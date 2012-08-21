(ns example.usage
  (:require pymorphy))

;; 1000 words
(def start-time (System/currentTimeMillis))

(def words (take 1000 (repeat "ПОСЕЛКИ")))
(def res (pymorphy/normalize words))
(println (count res))
(println (str "returns:" res))

(println (str "For 1000 words it takes " (- (System/currentTimeMillis) start-time) " millis"))

;; 1 word
(def start-time (System/currentTimeMillis))

(println (str "returns: " (pymorphy/normalize "ПОСЕЛКИ")))

(println (str "For 1 word it takes " (- (System/currentTimeMillis) start-time) " millis"))



