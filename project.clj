(defproject clojure-pymorphy "0.1.0-SNAPSHOT"
  :description "Clojure-pymorphy wraps the main functionality of the python pymorphy library.
  For more information look at official pymorphy page http://packages.python.org/pymorphy/"
  :url "https://github.com/hsestupin/clojure-pymorphy"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [jep/jep "1.0"]]
  :repositories {"jep-repo" ~(str (.toURI (java.io.File. "jep-repo")))})
