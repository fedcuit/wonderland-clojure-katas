(ns wonderland-number.finder)

(defn- same-digits
  [& nums]
  (->> nums
       (map #(set (str %)))
       (apply =))
  )

(defn wonderland-number
  []
  (->> (iterate #(+ 1 %) 1)
       (filter #(same-digits % (* % 2) (* % 3) (* % 4) (* % 5) (* % 6)))
       first
       )
  )

(defn- cube-sum
  [num]
  (->> num
       str
       vec
       (map #(int (Math/pow (int %) 3)))
       (reduce +)))

(defn wonderland-number-2
  []
  (->> (iterate #(+ 1 %) 1)
       (filter #(= % (cube-sum %)))
       first
       )
  )