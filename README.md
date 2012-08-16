# clojure-pymorphy

Clojure-pymorphy wraps the main functionality of the python pymorphy library.
For more information look at official pymorphy page: http://packages.python.org/pymorphy/

## Building

    lein deps
    lein jar

## Usage

For full example look at <b>src/example/usage.clj</b>

    (ns example.usage
        (:require pymorphy))

    ; returns "ПОСЕЛОК"
    (pymorphy/normalize "ПОСЕЛКИ")

    ; returns java.util.ArrayList object [ПОСЕЛОК, ЗАКАТ, СНЕГОПАД]
    (pymorphy/normalize '("ПОСЕЛКИ" "ЗАКАТА" "СНЕГОПАДУ"))

## License

Copyright © 2012

Distributed under the Eclipse Public License, the same as Clojure.
