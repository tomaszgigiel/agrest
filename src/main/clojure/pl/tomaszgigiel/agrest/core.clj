(ns pl.tomaszgigiel.agrest.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn fake-recordset [r c] 
  (repeat r (repeat c (apply str (repeat 5 "qwerty1234567890")))))

(defn -main
  "agrest: database multitool"
  [& args]

  (with-open [w (io/writer (first args) :encoding "UTF-8")]
    (doseq [a (fake-recordset 100000 100)] 
      (.write w (apply str (interpose ", " a)))
      (.newLine w)))
  (println "ok"))
