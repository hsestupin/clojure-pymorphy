(ns pymorphy.tests
  (:use clojure.test)
  (:require pymorphy))

(deftest normalize-word-test
  (testing "Test \"normalize-word\" function for word"
    (testing "ПОСЕЛКИ"
      (is (=
            (pymorphy/normalize "ПОСЕЛКИ")
            "ПОСЕЛОК")))
    (testing "ГАРАЖИ"
      (is (=
            (pymorphy/normalize "ГАРАЖИ")
            "ГАРАЖ")))
    (testing "ГОРИЗОНТУ"
      (is (=
            (pymorphy/normalize "ГОРИЗОНТУ")
            "ГОРИЗОНТ")))))

;(run-tests)