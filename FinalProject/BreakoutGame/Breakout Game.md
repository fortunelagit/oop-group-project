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
    int PERIOD1 = 10;
    int PERIOD2 = 7;
    int PERIOD3 = 5;
}

Kelas ini memiliki beberapa konstanta umum. WIDTH dan HEIGHT menyimpan dimensi dari papan permainan. Ketika bola melewati BOTTOM_EDGE, game dinyatakan selesai. N_OF_BRICKS menyatakan jumlah bata yang harus dihancurkan. INIT_PADDLE_X dan INIT_PADDLE_Y merupakan posisi awal dari paddle, sedangkan INIT_BALL_X dan INIT_BALL_Y merupakan posisi awal dari bola. PERIOD merupakan waktu dalam milisecond antara eksekusi task berturut-turut yang membentuk cycle game. Variasi PERIOD diberikan untuk variasi level. 

### Sprite.java

Kelas Sprite merupakan kelas dasar untuk semua objek yang terdapat dalam papan permainan, yakni bola, bata, dan paddle. Pada kelas ini, diberikan methods dan variable yang ada pada ketiga objek seperti getImage() atau getX().

Image getImage() {

        return image;
}

int getX() {

        return x;
}

### Ball.java

Kelas Ball merupakan kelas untuk objek bola. Method move() menggerakkan bola di papan permainan. Jika bola membentur batas papan permainan, arah bola berubah.

void setXDir(int x) {

    xdir = x;
}

void setYDir(int y) {

    ydir = y;
}

Dua method ini dipanggil jika bola membentur paddle atau bata.

### Paddle.java

Kelas paddle mengenkapsulasi objek paddle dalam game ini. Paddle dalam game ini dapat dikontrol dengan 2 cara, yakni menggunakan keyboard atau mouse.

Apabila pemain menggunakan keyboard, paddle dikontrol menggunakan tombol panah kanan dan kiri. Dengan menekan tombol panah, kita menetapkan arah gerak paddle. Dengan melepaskan tombol panah, kita menetapkan variable dx ke nol (0). 

Apabila pemain menggunakan mouse, pemain hanya perlu menarik (drag) paddle untuk menggerakkannya dan melepaskan mouse (release) untuk menghentikan pergerakannya.

void move() {

    x += dx;

    if (x <= 0) {

        x = 0;
    }

    if (x >= Commons.WIDTH - imageWidth) {

        x = Commons.WIDTH - imageWidth;
    }
}

Paddle hanya bergerak secara horizontal, karenanya kita hanya perlu memperbarui koordinat x saja. If conditions diperlukan untuk memastikan paddle tidak bergelak melewati papan permainan.

Ukuran paddle juga akan dibedakan tergantung level permainan yang telah dipilih.

### Brick.java

Kelas brick mengenkapsulasi objek brick dalam game ini.

private boolean destroyed;

Kita menyimpan status suatu bata (sudah dihancurkan atau belum) dalam variabel destroyed.

### Board.java

Kelas Board adalah kelas dimana kita meletakkan logika game. 

private void gameInit() {

    bricks = new Brick[Commons.N_OF_BRICKS];

    ball = new Ball();
    paddle = new Paddle();

    int k = 0;

    for (int i = 0; i < 5; i++) {

        for (int j = 0; j < 6; j++) {

            bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
            k++;
        }
    }

    timer = new Timer(Commons.PERIOD, new GameCycle());
    timer.start();
}

Pada method gameInit(), kita membuat sebuah bola, sebuah paddle, dan 30 bata. Lalu kita membuat dan memulai sebuah timer.

if (inGame) {

    drawObjects(g2d);
} else {

    gameFinished(g2d);
}

Bergantung pada variable inGame, kita akan memnggambar semua objek dengan method drawObjects() atau menyelesaikan game dengan method gameFinished().

private class GameCycle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        doGameCycle();
    }
}

Pada waktu tertentu, timer akan memanggil method actionPerformed(), yang memanggil method doGameCycle(), membuat sebuah siklus permainan.

private void doGameCycle() {

    ball.move();
    paddle.move();
    checkCollision();
    repaint();
}

Method doGameCycle() menggerakkan bola dan paddle. Kita mengecek apa ada benturan yang terjadi dan menggambar ulang layar.

private void checkCollision() {

    if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {

        stopGame();
    }
...

Apabila bola menabrak papan permainan bagian bawah, kita hentikan permainan.

for (int i = 0, j = 0; i < Commons.N_OF_BRICKS; i++) {

    if (bricks[i].isDestroyed()) {

        j++;
    }

    if (j == Commons.N_OF_BRICKS) {

        message = "Victory";
        stopGame();
    }
}

Kita cek apakah jumlah bata yang telah kita hancurkan sama dengan jumlah N_OF_BRICKS. Jika sama, kita menang.

### Breakout.java

Kelas Breakout berisikan fungsi main. 

## Dokumentasi


## Source

[http://zetcode.com/javagames/breakout/]

