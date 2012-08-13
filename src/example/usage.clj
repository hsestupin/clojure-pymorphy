(ns example.usage
  (:require pymorphy))

(def start-time (System/currentTimeMillis))

;(dotimes [i 100] (println (str i " : " (normalize-word "ПОСЕЛКИ"))))
(dotimes [i 20] (pymorphy/normalize "ПОСЕЛКИ"))

(println (str "It takes " (- (System/currentTimeMillis) start-time) " millis"))


