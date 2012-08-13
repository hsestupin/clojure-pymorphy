(ns pymorphy.tests
  (:use clojure.test)
  (:require pymorphy))

(deftest normalize-word-test
  (testing "Test \"normalize-word\" function for word"
    (testing "ПОСЕЛКИ"
      (is (=
            (pymorphy/normalize "ПОСЕЛКИ")
            "ПОСЕЛООК")))
    (testing "ГАРАЖИ"
      (is (=
            (pymorphy/normalize "ГАРАЖИ")
            "ГАРАЖ")))
    (testing "ГОРИЗОНТУ"
      (is (=
            (pymorphy/normalize "ГОРИЗОНТУ")
            "ГОРИЗОНТ")))))

(with-test
  (defn normalize [word]
    ))

(run-tests)