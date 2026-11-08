# Nama : M. Ismail Cahyadi
# Nim : F1D02410120

## 1. Topik dan Cara Menjalankan Program
Aplikasi sistem pakar ini mengambil topik mengenai penentuan status bahaya dan penanganan kebakaran pada lingkungan gedung atau pemukiman. Topik ini dipilih untuk memberikan penilaian awal kesiapsiagaan bencana berdasarkan indikator pemicu api serta kondisi perambatan api di lokasi kejadian. Program ini dikembangan menggunakan bahasa pemrograman java dengan memanfaatkan pustaka bawaan Java Swing untuk membangun antarmuka grafis (GUI) interatif. Untuk menjalankan aplikasi ini, sistem komputer memerlukan Java Development Kit (JDK) versi 11 atau versi yang lebih baru. Proses pengoperasian program dilakukan dengan membuka Terminal atau Command Prompt pada direktori tempat file 'MainFrame.java' disimpan. Kompilasi kode program dilakukan terlebih dahulu dengan mengeksekusi perintah 'javac MainFrame.java'. Setelah kompilasi berhasil tanpa error, aplikasi GUI dapat dijalankan dengan mengetikkan perintah 'java MainFrame'. Setelah jendela antarmuka aplikasi terbuka di layar, pengguna dapat memulai proses konsultasi dengan menekan tombol "Mulai Konsultasi". Pengguna kemudian cukup menjawab serangkaian pertanyaan interaktif yang muncul satu per satu dengan memilih tombol opsi "Yes" atau "No" sesuai dengan kondisi lingkungan yang terdeteksi. Hasil penelusuran inferensi bertahap per siklus, kesimpulan status bahaya, serta alur alasannya akan ditampilkan pada area utama aplikasi.

## 2. Daftar Sumber dan Tabel Aturan
### Daftar Sumber (2 Jurnal Ilmiah dan 1 Portal Resmi Damkar)
1. S1: Sutanti, N., Tjahjono, B., & Syaufina, L. (2020). Analisis Risiko Kebakaran di Kecamatan Tambora Kota Administrasi Jakarta Barat. Jurnal Tataloka (UNDIP), Vol. 22 No. 22, hal. 162-174
   URL: [https://doi.org/10.14710/tataloka.22.2.162-174](https://doi.org/10.14710/tataloka.22.2.162-174) (Diakses 4 November 2026)
2. S2: Dinas Penanggulangan Kebakaran dan Penyelamatan Provinsi DKI Jakarta. Layanan dan Edukasi Penanggulangan Kebakaran Pemukiman. Dinas Pemadam Kebakaran DKI Jakarta.
   URL: [https://pemadam.jakarta.go.id](https://pemadam.jakarta.go.id) (Diakses 4 November 2026)
3. S3: Tampubolon, M. V., Tambunan, L., & Agirahman, F. A. (2020). Studi Literatur Pencegahan Bahaya Kebakaran pada Pemukiman Masyarakat dan Penerapannya. Jurnal Arsitektura (UNS), Vol. 18 No. 2, hal. 351-360.
### Tabel Aturan IF-THEN
|Aturan|IF(Premis/Syarat)|Then(Hasil/Kesimpulan)|Sumber|
|:---|:---|:---|:---|
|R1|IF terdekteksi asap tebal AND terdengar bunyi alarm Kebakaran|Then terindikasi adanya titik api|S3|
|R2|IF terdapat bau korsleting listrik OR terdapat bau kebocoran gas|Then bahaya pemicu api aktif|S1|
|R3|IF terindikasi adanya titik api AND bahaya pemicu api aktif|THEN potensi kebakaran meluas|S1|
|R4|IF potensi kebakaran meluas AND api masih terlokalisir|THEN Status Siaga 2(Penanganan Mandiri dengan APAR)|S2|
|R5|IF Potensi kebakaran meluas AND api sudah merambat ke struktur bangunan|THEN Status Siaga 1(Bahaya Tinggi/Evakuasi dan Penggil Damkar)|S2|

## 3. Metode yang Dipilih dan Alasannya
Metode: Forward Chaining (FC)
Alasan: Metode Forward Chaining bekerja secara data-driven, yaitu dimulai dengan mengumpulkan fakta-fakta awal yang dikonfirmasi pengguna di lapangan (seperti asap tebal, bau gas/listrik, dan jangkauan api). Dari fakta-fakta tersebut, mesin inferensi secara bertahap mengeksekusi aturan berantai (R1 hingga R5) per siklus untuk menghasilkan fakta baru hingga mencapai kesimpulan tingkat status bahaya. Pendekatan ini sangat cocok untuk sistem kesiapsiagaan bencana kebakaran karena keputusan penanganan harus diambil berdasarkan temuan indikator rill yang ada di lokasi.

## 4. Tiga Kasus Uji dan Screenshoot
### Tabel Kasus Uji
|#|Jawaban "Yes" Pengguna(Fakta Awal)|Kesimpulan yang Diharapkan|
|:---|:---|:---|
|1|'terdeteksi asap tebal', 'terdengar bunyi alarm kebakaran', 'terdapat bau korsleting listrik', 'api masih terlokalisir'|Status Siaga 2(Penanganan Mandiri dengan APAR)|
|2|'terdeteksi asap tebal', 'terdengar bunyi alarm kebakaran', 'terdapat bau kebocoran gas', 'api sudah merambat ke struktur bangunan'|Status Siaga 1(Bahaya Tinggi/Evakuasi dan Panggil Damkar)|
|3| 'terdeteksi asap tebal', 'terdengar bunyi alarm kebakaran' (tanpa pemicu dan perambatan)|Status Aman/Potensi Rendah (Kasus Negatif)|
### Screenshot hasil Pengujian
![Kasus Uji 1](screenshotkasus-1.png)
![Kasus Uji 2](screenshotkasus-2.png)
![Kasus Uji 3](screenshotkasus-3.png)

## 5. Batas Keberlakuan
Aturan dalam sistem pakar ini disusun berdasarkan penelitian ilmiah dari Jurnal Tataloka UNDIP (Sutanti et., 2020), Jurnal Arsitektura UNS (Tampubolon et al., 2020), serta panduan edukasi resmi dari Dinas Penanggulangan Kebakaran dan Penyelamatan DKI Jakarta. Sistem ini dirancang khusus untuk penelitian awal dan kesiapsiagaan bahaya pada lingkungan atau bangunan gedung skala kecil hingga menengah. Hasil kesimpulan dan saran tindakan dari aplikasi ini merupakan panduan pertolongan pertama serta rekomendasi awal kesiapsiagaan mandiri, bukan instrumen atau keputusan resmi dari dinas pemadam kebakaran setempat.

## 6. Penggunaan Alat Bantu AI
Alat Bantu Ai digunakan dalam penyelesaian tugas ini sebagai media diksusi untuk perancangan basis pengetahuan, penyusunan referensi sitasi 3 sumber ilmiah/resmi, pembuatan antarmuka grafis Java GUI (Swing), serta debugging alur perulangan per siklus pada logika Forward Chaining.