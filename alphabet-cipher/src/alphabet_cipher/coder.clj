(ns alphabet-cipher.coder)

(def alphabet (map char (range (int \a) (inc (int \z)))))

(defn shifted-alphabet
  [ch]
  (take (count alphabet) (drop (- (int ch) (int \a)) (cycle alphabet)))
  )

(def smap (map shifted-alphabet alphabet))

(defn alha-index
  [l]
  (- (int l) (int \a))
  )

(defn encode
  [keyword message]
  (->> (partition 2 (interleave (cycle keyword) message))
       (take (count message))
       (map (fn [[k m]] (-> smap (nth (alha-index k)) (nth (alha-index m)))))
       (apply str))
  )

(defn decode
  [keyword message]
  (->> (partition 2 (interleave (cycle keyword) message))
       (take (count message))
       (map (fn [[k m]] (-> smap (nth (alha-index k)) (.indexOf m))))
       (map #(char (+ (int \a) %)))
       (apply str)
       )
  )

