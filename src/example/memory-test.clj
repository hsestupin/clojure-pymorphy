(ns example.memory-test
  (:require pymorphy))

(dotimes [n 200]
  (do
    (pymorphy/normalize (take 500 (repeat "ПОСЕЛКИ")))
    (Thread/sleep 20)
    (println n)))

