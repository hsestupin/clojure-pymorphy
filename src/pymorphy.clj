(ns pymorphy
  (:import (java.util ArrayList)
    (jep Jep)
    (java.io File))
  (:require [clojure.string :as str]
    [clojure.java.io :as io]))

(def jep-obj (new Jep))

(defn ^File create-file [parent file]
  (let [file-path (new File parent (str file ".shelve"))]
    (if (.createNewFile file-path)
      file-path
      (throw (new IllegalStateException (str "Couldn't create " file-path))))))


(defn ^File create-dicts [dict-folder]
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

(defn ^File get-dicts []
  (let [dict-folder (new File "shelve_ru")]
    (if (.exists dict-folder)
      dict-folder
      (create-dicts dict-folder))))

;(def morph
;  (let [dict-as-resource (io/resource "dicts/shelve_ru")
;        jep (doto (new Jep)
;              (.set "morph" (new Object))
;              (.set "db_path" (if dict-as-resource
;                                (.getFile dict-as-resource)
;                                (.getAbsolutePath (get-dicts))))
;              (.runScript (.getFile (io/resource "pymorphy/morph.py"))))]
;    (.getValue jep "morph")))

(defn jep-instance
  "Function initializes and returns instance of Jep python interpretator"
  [input script-path]
  (let [dict-as-resource (io/resource "dicts/shelve_ru")]
    (doto jep-obj
      (.set "input"
        (str/upper-case (if (coll? input)
          (str/join "," input)
          input)))
      (.set "response" (new ArrayList))
;      (.set "morph" morph)
      (.set "db_path" (if dict-as-resource
        (.getFile dict-as-resource)
        (.getAbsolutePath (get-dicts))))
      (.runScript script-path))))

(defn run-jep [input script-path]
  (let [jep (jep-instance input script-path)
        response (.getValue jep "response")]
    (if (== (count response) 1)
      (first response)
      (seq response))))
;
(defn normalize
  "Returns basic morphological form of the given word
or collection of words"
  [input]
  (let [python-tmp (File/createTempFile "tmp" ".py")
        python-script (io/resource "pymorphy/pymorphy.py")]
    (try
      (when (and python-script (.exists python-tmp))
        (with-open [python-os (.openStream python-script)]
          (clojure.java.io/copy python-os python-tmp))
        (run-jep input (.getAbsolutePath python-tmp)))
      (catch jep.JepException e
        (println (str "Error for input:\n" (str/join "^" input) "\nwith message: " (.getMessage e)))
        "ERROR")
      (finally
        (when (.exists python-tmp)
          (.delete python-tmp))))))





