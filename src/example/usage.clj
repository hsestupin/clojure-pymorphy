(ns example.usage
  (:require pymorphy))

(def start-time (System/currentTimeMillis))

(println (pymorphy/normalize '("ПОСЕЛКИ" "ЗАКАТА" "СНЕГОПАДУ")))

(def words (take 1000 (repeat "ПОСЕЛКИ")))
(def res (pymorphy/normalize words))
(println (count res))
(println (str "returns:" res))

(println (str "For 1000 words it takes " (- (System/currentTimeMillis) start-time) " millis"))


(def start-time (System/currentTimeMillis))

(def word "ПОСЕЛКИ")
(println (str "returns: "(pymorphy/normalize word)))

(println (str "For 1 word it takes " (- (System/currentTimeMillis) start-time) " millis"))



