(ns chess.svgs)

(defn svg-of [type]
  (case type
    p [:svg {:class "pawn" :viewBox [0 0 150 189]}
       [:path {:d ["M19.7787603" "160.611234 L19.6650028" "160.331515 C15.8585905" "151.05117 15.8585905" "119.433223 50.1356721" "98.9809948 C32.4613815" "83.6698183 35.4501975" "68.3161489 59.1021201" "52.9199867 C51.1685818" "36.1476863 59.1021201" "22.4908002 75.4994396" "22 C91.8978799" "22.484747 99.8312756" "36.1479878 91.8978799" "52.9199867 C115.549802" "68.3161489 118.538618" "83.6698183 100.864328" "98.9809948 C134.798639" "119.228701 135.137982" "150.419928 131.448017" "160.046437 L131.334997" "160.331515 C130.019892" "163.620035 128.289837" "166.044344 125.373868" "166 L25.6261319" "166 C22.7934762" "166.043041 21.0799367" "163.756551 19.7787603" "160.611234 Z"]}]]
    r [:svg {:class "rook" :viewBox [0 0 152 182]}
       [:path {:d ["M90.2913604" "17 L90.2913604" "28.0643763 L101.587286" "28.0643763 L101.587286" "17 L130.782059" "17 L130.782059" "50.7538443 C119.186197" "60.1264411 113.19967" "64.9778237 112.822476" "65.307992 C112.24925" "65.9039608 111.954119" "66.5510944 111.937085" "67.249393 L111.937085" "107.605645 L125.599864" "121.525616 L125.599864" "138.941452 C131.273549" "139.788458 138.255482" "139.833616 139" "143.701796 L139" "161.962148 L139" "161.962148 C139" "163.320716 138.210537" "164 136.631611" "164 L76.0648988" "164 L16.3683891" "164 C14.789463" "164 14" "163.320716 14" "161.962148 L14" "161.962148 L14" "143.701796 C14.7445179" "139.833616 21.7264513" "139.788458 27.4001364" "138.941452 L27.4001364" "138.941452 L27.4001364" "121.525616 L41.0629152" "107.605645 L41.0629152" "67.249393 C41.0458805" "66.5510944 40.75075" "65.9039608 40.1775237" "65.307992 C39.8003302" "64.9778237 33.8138028" "60.1264411 22.2179414" "50.7538443 L22.2179414" "50.7538443 L22.2179414" "17 L51.412714" "17 L51.412714" "28.0643763 L62.7086396" "28.0643763 L62.7086396" "17 L76.9351012" "17 L90.2913604" "17 Z"]}]]
    b [:svg {:class "bishop" :viewBox [0 0 168 168]}
       [:path {:d ["M84.9617222" "5 L84.9614804" "5.04105109 C92.3118816" "5.54054162 98.8837829" "10.1432511 100.791813" "18.4676282 C101.595635" "25.3637474 99.6329366" "29.4666762 95.9926501" "31.8758442 C113.443356" "37.3571863 125.77884" "50.2862479 129.293685" "56.7239239 C131.35541" "64.0027245 131.974045" "70.8794743 131.018694" "77.2598827 C129.293685" "87.3341267 119.686738" "100.089669 115.24706" "102.900125 C117.535178" "111.384803 127.255582" "129.140595 123.073825" "129.55468 C124.316705" "130.806134 114.081915" "132.778298 106.689257" "134.571998 C107.819852" "135.830852 108.382749" "137.078336 117.862591" "138.504398 C119.911285" "138.071036 119.890578" "136.777841 123.073825" "136.815886 C159.58194" "133.693856 163.261062" "141.656002 164" "144.20797 C161.868144" "154.878301 157.295064" "158.854456 153.093808" "163.850162 C144.901697" "164.541677 142.271545" "162.741461 137.317864" "160.467111 C128.500946" "159.257148 119.407234" "162.056562 103.704759" "161.9395 C96.3894735" "160.711686 89.7804867" "157.697969 84.4994836" "151.326396 C79.2195133" "157.697969 72.6105265" "160.711686 65.2952409" "161.9395 C49.5927656" "162.056562 40.4990543" "159.257148 31.6821356" "160.467111 C26.7284552" "162.741461 24.0983028" "164.541677 15.9061918" "163.850162 C11.7049358" "158.854456 7.1318559" "154.878301 5" "144.20797 C5.73893837" "141.656002 9.41805981" "133.693856 45.926175" "136.815886 C49.1094222" "136.777841 49.0887153" "138.071036 51.1374089" "138.504398 C60.6172512" "137.078336 61.1801484" "135.830852 62.3107427" "134.571998 C54.9180854" "132.778298 44.6832947" "130.806134 45.926175" "129.55468 C41.7444176" "129.140595 51.4648222" "111.384803 53.7529397" "102.900125 C49.3132615" "100.089669 39.7063152" "87.3341267 37.9813062" "77.2598827 C37.0259554" "70.8794743 37.6445902" "64.0027245 39.7063152" "56.7239239 C43.22116" "50.2862479 55.5566437" "37.3571863 73.0073499" "31.8758442 C69.3670634" "29.4666762 67.4043652" "25.3637474 68.2081871" "18.4676282 C70.1162171" "10.1432511 76.6881184" "5.54054162 84.0385196" "5.04105109 L84.0382778" "5 C84.1927943" "5.00326738 84.3470427" "5.00832654 84.5009876" "5.01517404 C84.6536251" "5.00830465 84.8075401" "5.0032603 84.9617222" "5 Z"]}]]
    q [:svg {:class "queen" :viewBox [0 0 180 166]}
       [:path {:d ["M94.8262987" "31.9488193 C96.6765316" "49.2768115 99.8320461" "64.004485 104.292842" "76.1318398 C113.763621" "60.2823673 119.272436" "46.9538107 120.819287" "36.1461702 C110.024011" "30.4052251 111.47411" "10.4295113 129.438966" "12.1100736 C146.235113" "15.2783617 143.559668" "35.4216811 129.653829" "38.5917714 C129.422742" "53.8259334 130.149263" "69.0900813 132.28702" "84.504915 C141.46647" "73.4025694 147.839374" "61.0934074 152.10473" "48.3056921 C140.111977" "34.5151687 153.203316" "22.8872992 165.33092" "25.8726628 C178.740508" "30.294389 178.90143" "49.8213521 161.396866" "51.2135606 C154.557291" "67.5025504 151.604418" "107.503081 151.576114" "107.473201 C144.974104" "113.067298 140.208693" "125.163682 139.064634" "135.475065 C141.838871" "141.317083 143.733671" "147.941978 145.176083" "154.969588 C129.03122" "163.139292 50.456245" "162.879937 34.8239171" "154.969588 C36.2663291" "147.941978 38.1611289" "141.317083 40.9353665" "135.475065 C39.5296076" "125.961599 33.4939976" "114.23893 28.4238865" "107.473201 C24.9709306" "81.3620231 21.6973466" "62.6088096 18.6031344" "51.2135606 C1.09856968" "49.8213521 1.25949192" "30.294389 14.6690799" "25.8726628 C26.7966839" "22.8872992 39.8880233" "34.5151687 27.8952704" "48.3056921 C34.1699354" "64.1842706 40.7758386" "76.2506783 47.71298" "84.504915 C49.850737" "69.0900813 50.5772584" "53.8259334 50.3461714" "38.5917714 C36.4403316" "35.4216811 33.7648869" "15.2783617 50.5610341" "12.1100736 C68.5258903" "10.4295113 69.9759885" "30.4052251 59.1807129" "36.1461702 C64.1612487" "51.2116447 69.6700637" "64.5402012 75.707158" "76.1318398 C81.5951644" "58.6228519 84.7506789" "43.8951785 85.1737013" "31.9488193 C68.7966152" "23.1279629 75.2201112" "5.25226459 89.9998492" "5.0025202 C104.779587" "4.75277581 111.203385" "23.1279629 94.8262987" "31.9488193 Z"]}]]
    k [:svg {:class "king" :viewBox [0 0 160 168]}
       [:path {:d ["M84.9385064" "5.52771375 C85.4390381" "7.59412589 85.7665622" "9.90088509 85.6091388" "12.8813478 C88.4160895" "13.5629 91.5520506" "14.8693686 95.0170222" "16.8007538 L95.5975639" "17.1284376 L95.5975639" "24.8859224 C92.6974809" "26.0103852 90.41676" "27.6170081 86.066643" "27.6126508 L85.6091388" "27.6063675 L85.6091388" "34.8690242 C98.6700882" "40.8577897 99.9246942" "47.0578074 100.371036" "53.2722875 L100.509613" "55.415486 L103.828128" "53.4453439 C109.497367" "50.1215921 114.735379" "47.6760475 126.341173" "48.4135572 C148.884179" "51.7370845 153.658196" "65.5626119 156" "77.1546389 C156" "100.559168 130.160578" "110.332064 129.371232" "125.6367 C129.371232" "133.740342 134.328804" "141.603228 136.184146" "156.282759 C131.800088" "158.962094 124.62137" "164 80.4996872" "164 C36.3780045" "164 29.1999117" "158.962094 24.8158541" "156.282759 C26.6711963" "141.603228 31.6287683" "133.740342 31.6287683" "125.6367 C30.8394224" "110.332064 5" "100.559168 5" "77.1546389 L5.14354959" "76.456539 C7.53987226" "65.0137916 12.5666809" "51.670614 34.6588275" "48.4135572 C48.4657195" "47.536175 53.2604218" "51.163704 60.4903871" "55.415486 L60.6289639" "53.2722875 C61.0753058" "47.0578074 62.3299118" "40.8577897 75.3908612" "34.8690242 L75.3908612" "27.6063675 C70.7397946" "27.728383 68.402522" "26.0491598 65.4024361" "24.8859224 L65.4024361" "17.1284376 C69.0984938" "15.0164606 72.4279688" "13.600764 75.3908612" "12.8813478 C75.2334378" "9.90088509 75.5609619" "7.59412589 76.0614936" "5.52771375 C77.5535072" "4.45157791 79.0377227" "4 80.5000594" "4 C81.9623962" "4 83.6332111" "4.58625115 84.9385064" "5.52771375 Z"]}]]
    n [:svg {:class "knight" :viewBox [0 0 176 174]}
       [:path {:d ["M56.4510087" "168 C53.8512286" "167.59598 52.3412201" "166.157372 51.9209833" "163.684176 C52.1499659" "163.719788 50.7453835" "150.287287 51.9209833" "147.784434 C53.096583" "145.281581 83.2208518" "109.154266 83.9237018" "107.764511 C84.3922685" "106.838008 84.3922685" "104.886262 83.9237018" "101.909272 C66.4647185" "110.053275 57.3071542" "114.536125 56.4510087" "115.357821 C55.1667904" "116.590366 42.7308266" "126.711686 40.2655056" "127.745906 C37.8001845" "128.780126 25.3346184" "131.1871 20.9665351" "130.614798 C16.5984518" "130.042496 10.5691559" "124.252296 9.20483732" "122.70966 C7.84051869" "121.167025 4.88551075" "110.637844 6.25592398" "107.764511 C7.1695328" "105.848957 13.5290976" "93.7690329 25.3346184" "71.5247402 C27.2123455" "66.3507992 27.2123455" "60.2972035 28.293109" "54.7251281 L40.2655056" "38.1617755 C41.3539084" "35.8605619 41.1670486" "33.5341588 39.7049262" "31.1825664 C39.7049262" "29.0623113 40.0574895" "14.344699 40.7058075" "11.703816 C41.3541255" "9.062933 42.8797132" "9.062933 44.3633118" "9.12841404 C45.3523775" "9.17206806 52.0978922" "13.8533727 64.5998559" "23.1723279 C70.229917" "14.3404414 73.5822757" "9.32629731 74.6569317" "8.12989564 C76.2689158" "6.33529314 75.3020881" "6.94164946 80.1133356" "8.12989564 C83.3208339" "8.92205976 87.3036312" "14.8291257 92.0617275" "25.8510934 C148.029743" "27.2391713 177.471382" "115.357821 169.798287" "163.684176 C169.466857" "165.735232 167.90606" "167.17384 165.115897" "168 L56.4510087" "168 Z"]}]]
    ""))
