(ns verify-links.core
  (:require (clojure set))
  (:gen-class))

;;----------------------------------------------------------------------
(defn complete-url-with-prefix [prefix url]
  (if (.contains url "http")
    url
    (str prefix "/" url)))

(defn url-prefix [url]
  (first (re-seq #"http://[^/]*" url)))

(defn print-br [e]
  (println "BROKEN LINK!!"))

(defn fetch-parse-url [url root-url]
  (do
    (print "Fetching url: " url " ... ")
    (set
     (try
       (let [urls
             (if (.contains url root-url)
               (->> url
                    slurp
                    (re-seq #"href=\"([^\"]*)")
                    (map second)
                    (map #(complete-url-with-prefix (url-prefix url) %1)))
               (do (slurp url) nil))]
         (println " Ok.")
         urls)
       (catch java.io.FileNotFoundException e (print-br e))
       (catch java.io.IOException e (print-br e))))))

(defn visit-urls [visited-urls urls root-url]
  (when-not (empty? urls)
   (let [next-urls
         (clojure.set/difference
          (clojure.set/union
           (fetch-parse-url (first urls) root-url)
           urls)
          #{(first urls)}
          visited-urls)
         next-visited-urls (clojure.set/union visited-urls #{(first urls)} )]
     (visit-urls next-visited-urls next-urls root-url))))

(defn verify-website [url]
  (println "Check the webiste " url)
  (visit-urls #{} #{url} url))

;;(verify-website "http://yves-ba.com")

(defn -main
  "I don't do a whole lot ... yet."
  [& urls]
  (println "VERIFY BROKEN LINKS." urls )
  (doseq [url urls] (verify-website url)))

;; ----------------------------------------------------------------------
;; ----------------------------TESTS-------------------------------------
;; ----------------------------------------------------------------------

;; (check-url "http://yves-ba.com")
;; (check-url "http://yves-ba.com/doesnotexist")

;; (map (comp check-url second) '(["href=\"blog.css" "blog.css"] ["href=\"" ""] ["href=\"links.html" "links.html"] ["href=\"resune.html" "resune.html"] ["href=\"http://htmlandcssbook.com/" "http://htmlandcssbook.com/"] ["href=\"http://htmlandcssbook.com/" "http://htmlandcssbook.com/"] ["href=\"http://subtlepatterns.com/" "http://subtlepatterns.com/"] ["href=\"http://www.alistapart.com/" "http://www.alistapart.com/"]))

;; (map check-url '("blog.css" "" "links.html" "resune.html" "http://htmlandcssbook.com/" "http://htmlandcssbook.com/" "http://subtlepatterns.com/" "http://www.alistapart.com/"))
