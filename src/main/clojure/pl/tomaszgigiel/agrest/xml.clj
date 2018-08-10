(ns pl.tomaszgigiel.agrest.xml
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [clojure.xml :as xml])
  (:gen-class))

(defn fake-recordset [r c] 
  (repeat r (repeat c (apply str (repeat 5 "qwerty1234567890")))))

(defn recordset-to-office-map [r]
  {:tag :Row :content (map (fn [[key value]] {:tag :Cell :content [{:tag :Data :content [value]}]}) r)})

(defn office-map-to-xml [r]
  (with-out-str (xml/emit-element r)))

(defn flatten-xml [x]
  (str/replace (str/replace x "\n<" "<") ">\n" ">"))

(defn -main
  "agrest: database multitool, xml"
  [& args]

  (with-open [w (io/writer (first args) :encoding "UTF-8")]
    (doseq [a (fake-recordset 100000 100)] 
      (.write w (apply str (interpose ", " a)))
      (.newLine w)))
  (log/info "ok (agrest: database multitool, xml)"))
