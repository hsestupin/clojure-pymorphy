(ns pymorphy.tests
  (:use clojure.test)
  (:require pymorphy))

(deftest normalize-word-test
  (testing "Test \"normalize-word\" function for word"
    (testing "ПоселкИ"
      (is (=
            (pymorphy/normalize "ПоселкИ")
            "ПОСЕЛОК")))
    (testing "гаражи"
      (is (=
            (pymorphy/normalize "гаражи")
            "ГАРАЖ")))
    (testing "горизонТУ"
      (is (=
            (pymorphy/normalize "горизонТУ")
            "ГОРИЗОНТ")))
    (testing "'(\"ЗаГадочному\" \"Смотрителя\")"
      (is (=
            (pymorphy/normalize '("ЗаГадочному" "Смотрителя"))
            (seq ["ЗАГАДОЧНЫЙ" "СМОТРИТЕЛЬ"])))))
  (testing "JepException handling raised during Jep call"
    (is (=
      (pymorphy/normalize '("ASD" "ASDASD" (new Exception "ff") "   ads" "223"))
      "ERROR"))))

;(run-tests)