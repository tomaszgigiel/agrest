(ns pl.tomaszgigiel.agrest.core-test
  (:use [clojure.test])
  (:require [clojure.data.xml :as dataxml])
  (:require [clojure.xml :as xml])
  (:require [pl.tomaszgigiel.agrest.core :as agrest]))

(deftest first-test
  (is (= 2 2)))

(deftest dataxml-test
  (let [r {:id "1" :name "Jan" :lastname "Abacki"}
        s "<?xml version=\"1.0\" encoding=\"UTF-8\"?><row id=\"1\" name=\"Jan\" lastname=\"Abacki\"></row>"]
    (testing "XML string creation" (is (= s (dataxml/emit-str (dataxml/element :row r))) "Expected string XML."))))

(deftest xml-test
  (let [r {:tag :Cell :content ["Abacki"]}
        s "<Cell>\nAbacki\n</Cell>\n"]
    (testing "XML string creation" (is (= s (with-out-str (xml/emit-element r)))) "Expected string XML.")))

(deftest agrest-test-map
  (let [r {:id "1" :name "Jan" :lastname "Abacki"}
        o {:tag :Row
           :content [{:tag :Cell :content [{:tag :Data :content ["1"]}]}
                     {:tag :Cell :content [{:tag :Data :content ["Jan"]}]}
                     {:tag :Cell :content [{:tag :Data :content ["Abacki"]}]}]}]
    (testing "map for office creation" (is (= o (agrest/recordset-to-office-map r))))))

(deftest agrest-test-xml
  (let [r {:tag :Row
           :content [{:tag :Cell :content [{:tag :Data :content ["1"]}]}
                     {:tag :Cell :content [{:tag :Data :content ["Jan"]}]}
                     {:tag :Cell :content [{:tag :Data :content ["Abacki"]}]}]}
        x (str "<Row>\n<Cell>\n<Data>\n1\n</Data>\n</Cell>\n<Cell>\n<Data>\nJan\n</Data>\n</Cell>\n<Cell>\n<Data>\nAbacki\n</Data>\n</Cell>\n</Row>\n")]
    (testing "office xml creation" (is (= x (agrest/office-map-to-xml r))))))

(deftest agrest-test-flatten-xml
  (let [a "<Row>\n<Cell>\n<Data>\n1\n</Data>\n</Cell>\n<Cell>\n<Data>\nJan\n</Data>\n</Cell>\n<Cell>\n<Data>\nAbacki\n</Data>\n</Cell>\n</Row>\n"
        b "<Row><Cell><Data>1</Data></Cell><Cell><Data>Jan</Data></Cell><Cell><Data>Abacki</Data></Cell></Row>"]
    (testing "office xml creation" (is (= b (agrest/flatten-xml a))))))
