# clojure-pymorphy

Clojure-pymorphy wraps the main functionality of the python pymorphy library.
For more information look at official pymorphy page: http://packages.python.org/pymorphy/

## Usage

src/example/usage.clj

(ns example.usage
  (:require pymorphy))

(pymorphy/normalize "ПОСЕЛКИ") ; returns "ПОСЕЛОК"

(pymorphy/normalize '("ПОСЕЛКИ" "ЗАКАТА" "СНЕГОПАДУ")) ; returns java.util.ArrayList object [ПОСЕЛОК, ЗАКАТ, СНЕГОПАД]

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
