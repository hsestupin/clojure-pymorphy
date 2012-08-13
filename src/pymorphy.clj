(ns pymorphy
  (:import  (java.util ArrayList)
            (jep Jep)))

(println (.getFile (clojure.java.io/resource "pymorphy/pymorphy.py")))

(defn jep-instance
  "Function initializes and returns instance of Jep python interpretator"
  [input]
  (doto (new Jep)
    (.set "input" input)
    (.set "response" (new ArrayList))
    (.set "db_path" (.getFile (clojure.java.io/resource "dicts/shelve_ru")))
    (.runScript (.getFile (clojure.java.io/resource "pymorphy/pymorphy.py")))))

(defn normalize
  "Returns basic morphological form of the given word"
  [word]
  (-> (jep-instance word) (.getValue "response") (.get 0)))


