# Breakout Game

## Pengertian

Breakout Game adalah sebuah game sederhana yang mengharuskan pemainnya untuk menghancurkan semua balok yang melayang di atas dengan menggunakan bola yang memantul ke sana kemari. Pemain juga harus menjaga agar bola tersebut tetap memantul dengan menggunakan paddle yang dapat digerakkan di bagian bawah.

## Kelas dan Interface

### program.BoardState.java

public interface Commons {

    int WIDTH = 300;
    int HEIGHT = 400;
    int BOTTOM_EDGE = 390;
    //int N_OF_BRICKS = 30;
    int INIT_PADDLE_X = 200;
    int INIT_PADDLE_Y = 360;
    int INIT_BALL_X = 230;
    int INIT_BALL_Y = 355;
    //int PERIOD1 = 10;
}

Kelas ini menginisialisasi beberapa konstanta umum. WIDTH dan HEIGHT menyimpan dimensi dari papan permainan. Ketika bola melewati BOTTOM_EDGE, game dinyatakan kalah atau game over. INIT_PADDLE_X dan INIT_PADDLE_Y merupakan posisi awal dari paddle, sedangkan INIT_BALL_X dan INIT_BALL_Y merupakan posisi awal dari bola. PERIOD merupakan waktu dalam milisecond antara eksekusi task berturut-turut yang membentuk cycle game. N_OF_BRICKS menyatakan jumlah bata yang harus dihancurkan. Inisialisasi PERIOD dan N_OF_BRICKS dihapus
dari BoardState.java dan dideklarasikan di Board.java, untuk memfasilitasi implementasi variasi level.

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

public Board(int n_of_bricks, int period, int difficulty) {
	loadData(); //membaca Breakout.txt untuk implementasi score
    
    //inisialisasi variabel yang mempengaruhi implementasi level
	this.n_of_bricks = n_of_bricks;
	this.period = period;
	this.difficulty = difficulty;
    
    initBoard();
    
    //metode untuk menjalankan musik saat game mulai
     try {
            URL url = this.getClass().getClassLoader().getResource("sounds/gameMusic.wav");
            AudioInputStream audioIn;
            audioIn = AudioSystem.getAudioInputStream(url);
			gameMusicSound = AudioSystem.getClip();
			gameMusicSound.open(audioIn);
			gameMusicSound.start();
	        gameMusicSound.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

Pada konstruktor Board(int n_of_bricks, int period, int difficulty) dilakukan inisialisasi variabel int n_of_bricks, int period, int difficulty, yang berguna untuk implementasi level. loadData() dipanggil untuk melakukan read file Breakout.txt, yang menyimpan skor dari game. Blok kode yang dikelilingi oleh try/catch memainkan music gameMusic.wav sat game dimulai.

private void initBoard() {

        addKeyListener(new TAdapter()); //memungkinkan pergerakan paddle menggunakan key dari keyboard
        
        //memungkinkan pergerakan paddle mengikuti mouse
        addMouseListener(new MAdapter()); 
        addMouseMotionListener(new MAdapter());
        
        setFocusable(true);
        setPreferredSize(new Dimension(BoardState.WIDTH, BoardState.HEIGHT));

        gameInit();
}

add

private void gameInit() {

        bricks = new Brick[n_of_bricks];

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < (n_of_bricks/6); i++) {

            for (int j = 0; j < 6; j++) {

                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }
        //playMusic();
        timer = new Timer(period, new GameCycle());
        data[TOTAL_GAMES_PLAYED_LOC]++;
        timer.start();
        saveData();
    }

### Breakout.java

Kelas Breakout berisikan fungsi main. 

## Dokumentasi


## Source

[http://zetcode.com/javagames/breakout/]

