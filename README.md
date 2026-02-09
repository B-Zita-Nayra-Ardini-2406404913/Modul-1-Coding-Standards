Nama  : Zita Nayra Ardini
NPM   : 2406404913
Kelas : Adpro - B

### Reflection 1
Dari source code yang ada pada modul 1, saya menemukan masalah bahwa setiap kali produk dibuat (create), nilai id-nya tidak ditentukan atau masih bernilai null. Hal ini tentunya menimbulkan permasalahan ketika aplikasi perlu melakukan operasi yang bergantung pada id produk, seperti edit maupun delete. Untuk mengatasi masalah tersebut, pada kedua fitur yang saya kerjakan saya menerapkan solusi dengan tetap mempertahankan tipe data id sebagai String, namun mengimplementasikan mekanisme auto-generate menggunakan UUID. Dengan pendekatan ini, setiap kali produk dibuat, sistem akan secara otomatis men-generate nilai UUID yang unik dalam bentuk String sebagai id produk. Implementasi ini menjadi efektif karena ketika fitur edit atau delete dipanggil, repository hanya perlu melakukan pencocokan sederhana berdasarkan id yang sudah dijamin unik untuk setiap produk, sehingga proses pencarian pasti efektif.

### Reflection 2
1. Setelah membuat unit test pada kode, saya merasa lebih lega karena kode yang saya buat sudah melakukan apa yang seharusnya dilakukan. Unit test harus dibuat sebanyak function yang ada di class tersebut beserta positif dan negatif case nya. Unit test dikatakan cukup ketika persenan kode yang telah diperiksa (code coverage) semakin tinggi mendekati 100%. Jika memiliki 100% code coverage pun, bisa saja ada bug/error lain misalnya terkait pembaruan dependensi yang didak disadari oleh developer. Jadi belum tentu sempurna.
2. Menurut saya, kode pada functional test yang baru menjadi tidak memenuhi aturan 'clean code'. Hal ini dikarenakan pada bagian @BeforeEach/setup driver seluruh data dummy dan variabel instance ditulis berulang di setiap kelas test yang mana membuat redudansi dan tidak efektif.
