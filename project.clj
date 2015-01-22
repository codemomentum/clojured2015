(defproject clojured-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 ;[com.runa/clj-hazelcast "1.0.1"]
                 [org.clojars.runa/clj-kryo "1.4.1"]
                 [com.hazelcast/hazelcast "3.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 ;distributed cache
                 [org.clojure/core.cache "0.6.4"]
                 ]
  :min-lein-version "2.0.0"
  :resource-paths ["resources"
                   "lib/clj-hazelcast-1.0.1.jar"])
