(ns example.memory-test
  (:require pymorphy))

;; If this test will raise OutOfMemoryException than memory leak has appeared again :(
(dotimes [n 500]
  (do
    (pymorphy/normalize (take 100 (repeat "ПОСЕЛКИ")))
    (Thread/sleep 10)
    (println n)))

