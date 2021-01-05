# Breakout Game

## Pengertian

Breakout Game adalah sebuah game sederhana yang mengharuskan pemainnya untuk menghancurkan semua balok yang melayang di atas dengan menggunakan bola yang memantul ke sana kemari. Pemain juga harus menjaga agar bola tersebut tetap memantul dengan menggunakan paddle yang dapat digerakkan di bagian bawah.

## Kelas-Kelas

### Commons.java

public interface Commons {

    int WIDTH = 300;
    int HEIGHT = 400;
    int BOTTOM_EDGE = 390;
    int N_OF_BRICKS = 30;
    int INIT_PADDLE_X = 200;
    int INIT_PADDLE_Y = 360;
    int INIT_BALL_X = 230;
    int INIT_BALL_Y = 355;
    int PERIOD = 10;
}

Kelas ini memiliki beberapa konstanta umum. WIDTH dan HEIGHT menyimpan dimensi dari papan permainan. Ketika bola melewati BOTTOM_EDGE, game dinyatakan selesai. N_OF_BRICKS menyatakan jumlah bata yang harus dihancurkan. INIT_PADDLE_X dan INIT_PADDLE_Y merupakan posisi awal dari paddle, sedangkan INIT_BALL_X dan INIT_BALL_Y merupakan posisi awal dari bola. PERIOD merupakan waktu dalam milisecond antara eksekusi task berturut-turut yang membentuk cycle game. 

### Sprite.java
### Ball.java
### Paddle.java
### Brick.java
### Board.java
### Breakout.java

