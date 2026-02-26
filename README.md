Nama  : Zita Nayra Ardini
NPM   : 2406404913
Kelas : Adpro - B

### Reflection 1 - Module 01
Dari source code yang ada pada modul 1, saya menemukan masalah bahwa setiap kali produk dibuat (create), nilai id-nya tidak ditentukan atau masih bernilai null. Hal ini tentunya menimbulkan permasalahan ketika aplikasi perlu melakukan operasi yang bergantung pada id produk, seperti edit maupun delete. Untuk mengatasi masalah tersebut, pada kedua fitur yang saya kerjakan saya menerapkan solusi dengan tetap mempertahankan tipe data id sebagai String, namun mengimplementasikan mekanisme auto-generate menggunakan UUID. Dengan pendekatan ini, setiap kali produk dibuat, sistem akan secara otomatis men-generate nilai UUID yang unik dalam bentuk String sebagai id produk. Implementasi ini menjadi efektif karena ketika fitur edit atau delete dipanggil, repository hanya perlu melakukan pencocokan sederhana berdasarkan id yang sudah dijamin unik untuk setiap produk, sehingga proses pencarian pasti efektif.

### Reflection 2 - Module 01
1. Setelah membuat unit test pada kode, saya merasa lebih lega karena kode yang saya buat sudah melakukan apa yang seharusnya dilakukan. Unit test harus dibuat sebanyak function yang ada di class tersebut beserta positif dan negatif case nya. Unit test dikatakan cukup ketika persenan kode yang telah diperiksa (code coverage) semakin tinggi mendekati 100%. Jika memiliki 100% code coverage pun, bisa saja ada bug/error lain misalnya terkait pembaruan dependensi yang didak disadari oleh developer. Jadi belum tentu sempurna.
2. Menurut saya, kode pada functional test yang baru menjadi tidak memenuhi aturan 'clean code'. Hal ini dikarenakan pada bagian @BeforeEach/setup driver seluruh data dummy dan variabel instance ditulis berulang di setiap kelas test yang mana membuat redudansi dan tidak efektif.

### Reflection - Module 02
1. Selama pengerjaan exercise, saya menemukan dan memperbaiki beberapa isu kualitas kode yang terdeteksi oleh SonarCloud. Berikut permasalahan yang ditemukan:
- Generic Exceptions (Maintainability - Medium): Ditemukan penggunaan generic exception seperti Exception atau RuntimeException pada ProductRepository.java. Strategi perbaikannya adalah membuat custom exception yang lebih deskriptif. Hal ini bertujuan agar penanganan error menjadi lebih spesifik dan memudahkan proses debugging.
- Empty Methods (Maintainability - High): Ditemukan method kosong tanpa komentar atau implementasi pada file tes seperti EshopApplicationTests.java dan ProductRepositoryTest.java. Strategi perbaikannya adalah memberikan komentaryang menjelaskan alasan method tersebut kosong.
- Security Hotspots - Missing Dependency Verification: SonarCloud mendeteksi risiko keamanan karena tidak adanya verifikasi integritas pada pihak ketiga (dependencies). Strategi perbaikannya adalah dengan menjalankan perintah ./gradlew --write-verification-metadata sha256 help untuk menghasilkan file verification-metadata.xml.
- HTML Accessibility - Anchor Tags as Buttons (Reliability - Low): Ditemukan penggunaan tag anchor (<a>) yang difungsikan sebagai tombol pada ProductList.html. Strategi perbaikannya adalah mengganti tag tersebut dengan elemen <button> yang lebih tepat penggunaannya.
- Unit Test Coverage: Walaupun unit test sudah ada secara lokal, terjadi isu coverage 0% pada laporan SonarCloud. Strategi perbaikannya adalah dengan mengonfigurasi JaCoCo di build.gradle agar menghasilkan laporan dalam format XML dan memastikan path laporan tersebut terbaca dengan benar oleh SonarScanner di GitHub Actions.
2. Implementasi yangs saya sudah memenuhi definisi Continuous Integration (CI) karena setiap push dan pull request ke semua branch sudah otomatis menjalankan unit test. Test yang dilakukan menggunakan ./gradlew test melalui GitHub Action dan juga menjalankan analisis kode menggunakan SonarQube. Hal ini memastikan bahwa setiap perubahan kode dapat langsung diperiksa secara otomatis tanpa harus menjalankan test secara manual.
Untuk Continuous Deployment (CD), implementasi sudah memenuhi definisi karena setiap push ke branch main secara otomatis men-trigger redeploy ke Koyeb menggunakan Dockerfile. Artinya, setiap perubahan yang berhasil di-merge ke main akan langsung terdeploy ke production environment tanpa intervensi manual.





