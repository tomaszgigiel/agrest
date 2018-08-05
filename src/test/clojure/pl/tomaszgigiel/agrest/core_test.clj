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

(deftest agrest-test
  (let [r {:id 1 :name "Jan" :lastname "Abacki"}
        s "<Row><Cell><Data ss:Type=\"Number\">1</Data></Cell><Cell><Data ss:Type=\"String\">Jan</Data></Cell><Cell><Data ss:Type=\"String\">Abacki</Data></Cell></Row>"]
    (testing "XML string creation" (is (= s (agrest/row-to-office-xml r)))) "Expected string XML."))
