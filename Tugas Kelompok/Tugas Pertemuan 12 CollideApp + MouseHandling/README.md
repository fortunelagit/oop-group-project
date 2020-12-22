# Collision Detection  (Mouse handling)

Program ini adalah contoh penggunaan Collision Detection, salah satu physics yang sering digunakan dalam game.
pada program ini contoh yang akan digunakan adalah Alien Shooter.


Untuk contoh kali ini akan digunakan dua tipe collision, ketika proyektil rudal mengenai target, dan ketika pesawat menabrak target.
  
Class yang terdapat di program sebagai berikut :

## Sprite.java
  
kode yang terdapat pada class ini akan diturunkan ke class SpaceShip, Alien, dan Missile untuk _rendering_ sprite, serta memberikan hitbox yang akan digunakan dalam deteksi collision.
  
## SpaceShip.java
kode pada class ini merepresentasikan pesawat yang digerakkan oleh pemain. 
  
   `private List<Missile> missiles;`

semua rudal yang ditembakkan pesawat akan disimpan pada list, 
  
`public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}`
  
rudal baru yang ditembakkan pesawat akan berada pada list Missile, hingga rudal yang ada di list menabrak target atau keluar dari layar.

Pada class SpaceShip.java pada program collision detection mouse-handling ini,terdapat perubahan besar pada method. 

Menghapus dan menyesuaikan method dengan event 'Key', keyPressed() diubah menjadi buttonPressed() dengan isi method yang digunakan untuk meng-capture Mouse Button 1 (Left Click) — sebagai tombol untuk menembak rudal. Method keyRelease() dihilangkan karena fungsi pergerakan pesawat sudah berada pada method move().

Pada method move() sekarang membutuhkan parameter tipe data MouseEvent. method move() ini sekarang digunakan untuk mengolah data dari class MAdapter sehingga pesawat dapat berada sesuai dengan posisi mouse, yang artinya pergerakan pesawat mengikuti pergerakan mouse.
  
## Board.java
  
Class ini digunakan untuk membuat board yang akan dipakai sebagai arena game. Objek pesawat dan target akan berada pada board ini.
  
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
  
kode ini akan memberikan koordinat tempat awal munculnya target.

public void initAliens() {
    
    aliens = new ArrayList<>();

    for (int[] p : pos) {
        aliens.add(new Alien(p[0], p[1]));
    }
}

kode di atas akan digunakan untuk menginisialisasi objek target serta memberinya koordinat tempat muncul.

Pada class Board.java terdapat beberapa perubahan. 

Menghilangkan method updateShip() karena tidak sesuai penggunaannya dengan method move() yang ada pada class SpaceShip.java

Perubahan class TAdapter yang semulanya meng-extend KeyAdapter menjadi MouseAdapter, lalu ada pula penyesuaian method sehingga program dapat mendeteksi tombol mouse yang dipencet, data class ini akan diolah pada method buttonPressed di class SpaceShip.java.

Penambahan class MAdapter yang mengimplementasi MouseMotionListener. Fungsi dari class ini adalah untuk mendapat koordinat atau lokasi mouse pada aplikasi. Kemusian koordinat  akan diolah pada method move() milik class SpaceShip.java.


## Alien.java
  
Class Alien ini adalah class target yang akan ditembak pemain, untuk kode pergerakan target sebagai berikut :
  
`public void move() {

    if (x < 0) {
        x = INITIAL_X;
    }

    x -= 1;
}`
  
Target ini kemudian akan kembali ke bagian kanan layar setelah melebihi batas layar sebelah kiri.
  
## Missile.java
  
Class rudal ini akan bergerak ke satu arah saja, kemudian akan menghilang setelah mencapai batas layar sebelah kanan. berikut adalah kode pergerakan rudal : 
  
`public void move() {

    if (x < 0) {
        x = INITIAL_X;
    }

    x -= 1;
}`

  
## CollisionEx.java
  
Class ini adalah class utama yang digunakan untuk menjalankan program.

# Dokumentasi


  ![](https://raw.githubusercontent.com/fortunelagit/oop-group-project/main/Tugas%20Kelompok/Tugas%20Pertemuan%2012%20CollideApp%20%2B%20MouseHandling/collision%20detection%20mouse%20handling%20diagram.jpg)


##  Video
https://youtu.be/V9MFcdo0jLo
