# clojure-pymorphy

Clojure-pymorphy wraps the main functionality of the python pymorphy library.
For more information look at official pymorphy page: http://packages.python.org/pymorphy/

Pymorphy on github https://github.com/kmike/pymorphy

Remember when your call some library method using built-in dictionaries at the first time
than directory "%dict_name%_ru" (eg "shelve_ru") will be created in your clojure-script folder

## Import in your project.clj

1) setup python pymorphy library and 

2) setup Java Embedded Python lubrary (jep) http://sourceforge.net/projects/jepp/

Put clojure-pymorphy to the local repo

    lein install

Add lein dependency
    
    [clojure-pymorphy "0.1.0"]

## Building

    lein deps
    lein jar

## Usage

For full example look at <b>src/example/usage.clj</b>

    (ns example.usage
        (:require pymorphy))

    ; returns "ПОСЕЛОК"
    (pymorphy/normalize "ПОСЕЛКИ")

    ; returns vector [ПОСЕЛОК, ЗАКАТ, СНЕГОПАД]
    (pymorphy/normalize '("ПОСЕЛКИ" "ЗАКАТА" "СНЕГОПАДУ"))

## Test

    lein test

## License

Copyright © 2012

Distributed under the Eclipse Public License, the same as Clojure.
