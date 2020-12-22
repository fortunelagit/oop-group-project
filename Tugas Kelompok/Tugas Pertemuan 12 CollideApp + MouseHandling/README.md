# Collision Detection
Craft: pesawat.
Missile : rudal.
Sederhananya, kita perlu mendeteksi ketika dua objek bertabrakan di layar.
Kita akan mendeteksi dua jenis tabrakan: saat rudal menghantam kapal alien dan saat pesawat ruang angkasa kita bertabrakan dengan alien.  
  
Berikut class-class yang ada dalam program ini :

## Sprite.java
  
Kita memiliki pesawat luar angkasa dan alien. Kita bisa memindahkan pesawat ruang angkasa di papan dengan menggunakan tombol kursor. Rudal yang menghancurkan alien diluncurkan dengan tombol spasi.

Kode yang dapat dibagikan oleh semua sprite (pesawat, alien, dan rudal) ditempatkan di Spriteclass.
getBounds()method persegi panjang berlari dari gambar sprite. Kita membutuhkan batasan dalam deteksi tabrakan.
Kode dalam class ini akan diturunkan ke class SpaceShip, Alien, dan Missile untuk _rendering_ sprite, serta memberi ‘hitbox’ yang akan digunakan dalam collision detection.
  
## SpaceShip.java
Class ini merepresentasikan pesawat kursor yang digerakkan oleh pemain. 


Class ini mewakili pesawat luar angkasa.


`rudal private List <Missile>;`

  
   `private List<Missile> missiles;`

semua rudal yang ditembakkan pesawat akan disimpan pada list, 
  
``public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}``
  
rudal baru yang ditembakkan pesawat akan berada dalam Missile list, hingga rudal yang ada di list menabrak target atau keluar dari layar.
 

Pada class SpaceShip.java pada program collision detection mouse-handling ini,terdapat perubahan besar pada method.
Menghapus dan menyesuaikan method dengan event 'Key', keyPressed() diubah menjadi buttonPressed() dengan isi method yang digunakan untuk meng-capture Mouse Button 1 (Left Click) — sebagai tombol untuk menembak rudal. Method keyRelease() dihilangkan karena fungsi pergerakan pesawat sudah berada pada method move().
Pada method move() sekarang membutuhkan parameter tipe data MouseEvent. method move() ini sekarang digunakan untuk mengolah data dari class MAdapter sehingga pesawat dapat berada sesuai dengan posisi mouse, yang artinya pergerakan pesawat mengikuti pergerakan mouse.

## Board.java
    Ini adalah koordinat posisi awal kapal alien.
`public void initAliens() {
        
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }`

initAliens()Method menciptakan list objek alien. Alien mengambil posisi awal mereka dari posarray.
Class ini digunakan untuk membuat board yang akan dipakai sebagai arena game
Objek pesawat dan target akan berada dalam board ini.

`private final int[][] pos = {
    {2380, 29}, {2500, 59}, {1380, 89},
    {780, 109}, {580, 139}, {680, 239},
    {790, 259}, {760, 50}, {790, 150},
    {980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60},
    {940, 59}, {990, 30}, {920, 200},
    {900, 259}, {660, 50}, {540, 90},
    {810, 220}, {860, 20}, {740, 180},
    {820, 128}, {490, 170}, {700, 30}
};`
 
 
 `private void drawObjects (Graphics g) {

    if (spaceship.isVisible ()) {
        g.drawImage (spaceship.getImage (), spaceship.getX (), spaceship.getY (),
                this);
    }
...
} `       


drawObjects()method menarik sprite di jendela. Pertama, kita menggambar pesawat sprite.
   Dalam lingkaran ini kita menggambar semua alien; mereka ditarik hanya jika belum pernah dihancurkan sebelumnya. Ini diperiksa dengan isVisible()method.

`g.setColor (Color.WHITE);
g.drawString ("Aliens left:" + aliens.size (), 5, 15); `
Di sudut kiri atas jendela, kita menggambar berapa banyak alien yang tersisa.
drawGameOver()menarik permainan selama pesan di tengah jendela. 

Pesan tersebut ditampilkan di akhir permainan, baik saat kita menghancurkan semua kapal alien maupun saat kita bertabrakan dengan salah satunya.


Di dalam updateAliens()method ini, pertama-tama kita memeriksa apakah ada objek asing yang tersisa di alienslist. Permainan selesai jika listnya kosong. Jika tidak kosong, kita pergi melalui list dan memindahkan semua itemnya. Alien yang dihancurkan dihapus dari list.


 checkCollisions()method pemeriksaan untuk kemungkinan tabrakan. 

Pertama, kita memeriksa apakah objek pesawat bertabrakan dengan salah satu objek alien. 

Kita mendapatkan persegi panjang objek dengan getBounds()method.  intersects()Method pemeriksaan jika dua persegi panjang berpotongan.

Pada class Board.java terdapat beberapa perubahan.
Menghilangkan method updateShip() karena tidak sesuai penggunaannya dengan method move() yang ada pada class SpaceShip.java
Perubahan class TAdapter yang semulanya meng-extend KeyAdapter menjadi MouseAdapter, lalu ada pula penyesuaian method sehingga program dapat mendeteksi tombol mouse yang dipencet, data class ini akan diolah pada method buttonPressed di class SpaceShip.java.
Penambahan class MAdapter yang mengimplementasi MouseMotionListener. Fungsi dari class ini adalah untuk mendapat koordinat atau lokasi mouse pada aplikasi. Kemusian koordinat akan diolah pada method move() milik class SpaceShip.java.

## Alien.java
  
`public void move () {

    jika (x <0) {
        x = INITIAL_X;
    }

    x - = 1;
}`
Alien kembali ke layar di sisi kanan setelah mereka menghilang di sisi kiri.
  
## Missile.java
  
`public void move() {
    
    x += MISSILE_SPEED;
    
    if (x > BOARD_WIDTH)
        visible = false;
}`
Rudal hanya bergerak ke satu arah. Mereka menghilang setelah mencapai batas jendela kanan.

  
## CollisionEx.java
  
Class ini adalah class utama yang digunakan untuk menjalankan program.

# Dokumentasi

### Diagram class
  ![](https://raw.githubusercontent.com/fortunelagit/oop-group-project/d130d2a3aa8f7210d9e811c2378138c956539a5e/Tugas%20Kelompok/Tugas%20Pertemuan%2012%20CollideApp%20%2B%20MouseHandling/collision%20detection%20mouse%20handling%20diagram.jpg)

### Screen-recording game  
  Pada link berikut
  https://youtu.be/V9MFcdo0jLo
  
