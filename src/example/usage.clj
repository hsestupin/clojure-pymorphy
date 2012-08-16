(ns example.usage
  (:require pymorphy))

(println (pymorphy/normalize '("ПОСЕЛКИ" "ЗАКАТА" "СНЕГОПАДУ")))

;; 1000 words
(def start-time (System/currentTimeMillis))

(def words (take 1000 (repeat "ПОСЕЛКИ")))
(def res (pymorphy/normalize words))
(println (count res))
(println (str "returns:" res))

(println (str "For 1000 words it takes " (- (System/currentTimeMillis) start-time) " millis"))

;; 1 word
(def start-time (System/currentTimeMillis))

(def word "ПОСЕЛКИ")
(println (str "returns: "(pymorphy/normalize word)))

(println (str "For 1 word it takes " (- (System/currentTimeMillis) start-time) " millis"))



