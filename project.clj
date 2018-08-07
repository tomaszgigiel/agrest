(defproject agrest "1.0.0.0-SNAPSHOT"
  :description "agrest: database multitool"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.apache.poi/poi "3.17"]
                 [org.apache.poi/poi-ooxml "3.17"]]
  :main ^:skip-aot pl.tomaszgigiel.agrest.core

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all :jar-name "agrest.jar" :uberjar-name "agrest-uberjar.jar"}
             :smallmemmory {:jmx-opts ["-Xmx512m"]}})
