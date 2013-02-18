(ns pymorphy
  (:import
    (java.util ArrayList)
    (jep Jep)
    (java.io File))
  (:require
    [clojure.string :as str]
    [clojure.java.io :as io]))

(def jep (new Jep))

(defn- ^File create-file [parent file]
  (let [file-path (new File parent (str file ".shelve"))]
    (if (.createNewFile file-path)
      file-path
      (throw (new IllegalStateException (str "Couldn't create " file-path))))))


(defn- ^File create-dicts [dict-folder]
  (let [dict-files ["endings" "freq" "lemmas" "misc" "rules"]]
    (when (not (.mkdir dict-folder))
      (throw (new IllegalStateException (str "Couldn't create a directory" dict-folder))))
    (dorun (map
      (fn [dict]
        (println (str "create-dict-file: " (io/resource (str "dicts/shelve_ru/" dict ".shelve"))))
        (with-open [os (.openStream (io/resource (str "dicts/shelve_ru/" dict ".shelve")))]
          (clojure.java.io/copy os (create-file dict-folder dict))))
      dict-files))
    dict-folder))

(defn- ^File get-dicts []
  (let [dict-folder (new File "shelve_ru")]
    (if (.exists dict-folder)
      dict-folder
      (create-dicts dict-folder))))

(defn- get-db-path [dict-as-resource]
  (if dict-as-resource (.getFile dict-as-resource) (.getAbsolutePath (get-dicts))))

(defn- setup-morph [jep]
  (let [dict-as-resource (io/resource "dicts/shelve_ru")
        db-path (get-db-path dict-as-resource)]
    (doto jep
      ;      (.eval "# -*- coding: utf-8 -*-") ; \nfrom pymorphy import get_morph")
      (.eval (str "from pymorphy import get_morph"))
      (.eval (str "morph = get_morph('" db-path "', 'shelve')")))))

(defn- eval-normalize [jep]
  (let [python-tmp (File/createTempFile "tmp" ".py")
        python-script (io/resource "pymorphy/pymorphy.py")]
    (try
      (when (and python-script (.exists python-tmp))
        (with-open [python-os (.openStream python-script)]
          (clojure.java.io/copy python-os python-tmp))
        (.runScript jep (.getAbsolutePath python-tmp)))
      (catch jep.JepException e
        (println "Error running python script: " (.getMessage e)))
      (finally
        (when (.exists python-tmp)
          (.delete python-tmp))))))

(setup-morph jep)
(eval-normalize jep)

; public

(defn normalize
  "Returns basic morphological form of the given word or collection of words.
  If input is a collection of the words function returns vector of normalized forms."
  [input]
  (when (nil? input)
    (throw (new IllegalArgumentException "Input word couldn't be nil")))
  (try
    (let [words (str/upper-case (if (coll? input) (str/join "," input) input))
          response (.invoke jep "normalize" (into-array String [words]))]
      (if (> (.indexOf response ",") -1)
        (str/split response #",")
        response))
    (catch jep.JepException e
      (println "For input: " (str/join "," input))
      (println "Shit had happened: " (.getMessage e))
      "ERROR")))






