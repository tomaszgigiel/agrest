(defproject agrest "1.0.0.0-SNAPSHOT"
  :description "agrest: database multitool"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.apache.poi/poi "3.17"]
                 [org.apache.poi/poi-ooxml "3.17"]
                 [org.clojure/tools.logging "0.4.1"]
                 ; otherwise log4j.properties has no effect
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.clojure/java.jdbc "0.7.7"]
                 [org.apache.derby/derby "10.4.2.0"]
                 [org.apache.derby/derbyclient "10.4.2.0"]
                 [org.apache.derby/derbynet "10.4.2.0"]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all :jar-name "truskawka.jar" :uberjar-name "truskawka-uberjar.jar"}
             :main-xml {:main ^:skip-aot pl.tomaszgigiel.agrest.xml}
             :main-database {:main ^:skip-aot pl.tomaszgigiel.agrest.database}
             :dev {:resource-paths ["src/test/resources"] :jmx-opts ["-Xmx512m"]}}
  :aliases {"run-main-xml-dev" ["with-profile" "main-xml,dev" "run"]
            "run-main-database-dev" ["with-profile" "main-database,dev" "run"]})
