(ns clojured-demo.core)

(require '[clj-hazelcast.core :as hz]
         '[clj-hazelcast.mr :as mr]
         '[clojure.tools.logging :as log]
         '[clj-hazelcast.mr :as mr]
         '[clj-hazelcast.query :as q])

(import '(java.util.concurrent TimeUnit))


;distributed map
(def mymap (atom nil))
(hz/init)
(reset! mymap (hz/get-map "mymap"))

;basic crud
@mymap
(hz/put! @mymap 1 "clojure java lisp")
(hz/put! @mymap 1 "clojure java lispxx")
(hz/put! @mymap 2 "lisp clojure clojure")
(get @mymap 1)
(get @mymap 2)
(keys @mymap)
(hz/clear! @mymap)


;mr
(mr/defmapper mapper1
              [k v]
              (let [words (re-seq #"\w+" v)]
                (partition 2 
                           (interleave words 
                                         (take (count words) 
                                               (repeatedly (fn [] 1)))))))

(mr/defreducer reducer1 [k v acc] (if (nil? acc) v (+ acc v)))

;execute mr
(let [tracker (mr/make-job-tracker @hz/hazelcast)
      fut (mr/submit-job
            {:map @mymap
             :mapper-fn mapper1
             :reducer-fn reducer1
             :tracker tracker})
      res (.get fut 2 TimeUnit/SECONDS)]
  res)

;distributed query
(q/defpredicate java? [k v] (.contains v "java"))

;execute query
(q/values @mymap java?)


