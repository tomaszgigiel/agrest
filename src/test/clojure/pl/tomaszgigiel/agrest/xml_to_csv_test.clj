(ns pl.tomaszgigiel.agrest.xml-to-csv-test
  (:use [clojure.test])
  (:require [clojure.data.xml :as dataxml])
  (:require [clojure.data.zip :as datazip])
  (:require [clojure.data.zip.xml :as datazipxml])
  (:require [clojure.java.io :as io])
  (:require[clojure.string :as string])
  (:require [clojure.xml :as xml])
  (:require [clj-xpath.core :as xpath])
  (:require [pl.tomaszgigiel.agrest.xml :as agrest]))

(def simple-xml-parsed-1 (dataxml/parse-str (slurp (io/resource "simple.xml"))))
(def simple-xml-parsed-2 (xml/parse (str (io/resource "simple.xml"))))

(deftest xml-to-csv-test
  (testing "?" (is (= simple-xml-parsed-1 simple-xml-parsed-2) "?"))
  (testing "?" (is (= "a" simple-xml-parsed-2) "?")))

;;;;;;;;;;;;;;;;;;;
;; http://kyleburton.github.io/clj-xpath/site/
(defn visit-nodes
  ([path nodes f]
     (vec
      (mapcat
       #(vec
         (cons
          ;; invoke the callback on the each of the nodes
          (f (conj path (:tag %1)) %1)
          ;; visit each of the children of this node
          (visit-nodes
           (conj path (:tag %1))
           (xpath/$x "./*" %1) f)))
       nodes))))

(defn all-paths [doc]
  (map
   #(str "/" (string/join "/" (map name %1)))
   (first
    (reduce
     (fn [[acc set] p]
       (if (contains? set p)
         [acc set]
         [(conj acc p) (conj set p)]))
     [[] #{}]
     (visit-nodes []
                  (xpath/$x "./*" doc)
                  (fn [p n]
                    p))))))


(comment
(= #clojure.data.xml.Element{:tag :books, :attrs {}, :content (#clojure.data.xml.Element{:tag :book, :attrs {}, :content (#clojure.data.xml.Element{:tag :title, :attrs {}, :content ("Clojure in Action")} #clojure.data.xml.Element{:tag :author, :attrs {}, :content (#clojure.data.xml.Element{:tag :first-name, :attrs {}, :content ("Amit")} #clojure.data.xml.Element{:tag :last-name, :attrs {}, :content ("Rathore")})} #clojure.data.xml.Element{:tag :author, :attrs {}, :content (#clojure.data.xml.Element{:tag :first-name, :attrs {}, :content ("Francis")} #clojure.data.xml.Element{:tag :last-name, :attrs {}, :content ("Avila")})})} #clojure.data.xml.Element{:tag :book, :attrs {}, :content (#clojure.data.xml.Element{:tag :title, :attrs {}, :content ("The Joy of Clojure")} #clojure.data.xml.Element{:tag :author, :attrs {}, :content (#clojure.data.xml.Element{:tag :first-name, :attrs {}, :content ("Michael")} #clojure.data.xml.Element{:tag :last-name, :attrs {}, :content ("Fogus")})} #clojure.data.xml.Element{:tag :author, :attrs {}, :content (#clojure.data.xml.Element{:tag :first-name, :attrs {}, :content ("Chris")} #clojure.data.xml.Element{:tag :last-name, :attrs {}, :content ("Houser")})})})} 
   {:tag :books, :attrs nil, :content [{:tag :book, :attrs nil, :content [{:tag :title, :attrs nil, :content ["Clojure in Action"]} {:tag :author, :attrs nil, :content [{:tag :first-name, :attrs nil, :content ["Amit"]} {:tag :last-name, :attrs nil, :content ["Rathore"]}]} {:tag :author, :attrs nil, :content [{:tag :first-name, :attrs nil, :content ["Francis"]} {:tag :last-name, :attrs nil, :content ["Avila"]}]}]} {:tag :book, :attrs nil, :content [{:tag :title, :attrs nil, :content ["The Joy of Clojure"]} {:tag :author, :attrs nil, :content [{:tag :first-name, :attrs nil, :content ["Michael"]} {:tag :last-name, :attrs nil, :content ["Fogus"]}]} {:tag :author, :attrs nil, :content [{:tag :first-name, :attrs nil, :content ["Chris"]} {:tag :last-name, :attrs nil, :content ["Houser"]}]}]}]})
)
