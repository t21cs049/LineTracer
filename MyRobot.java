/**
 * ロボットクラスの作成例：単純なライントレーサーロボット
 */
public class MyRobot extends Robot {
	/**
	 * 実行用関数
	 */
	private int beforeA = WHITE;
	private int beforeC = WHITE;
	private int continuityNumber = 0;

	public void run() throws InterruptedException {
		while (true) {
			// 線を見失ったとき用に最後のLIGHTの情報を保持する

			// デバッグ用
			System.out.println("A:" + getColor(LIGHT_A) + " B:" + getColor(LIGHT_B) + " C:" + getColor(LIGHT_C));

			// 右センサの色に応じて分岐
			switch (getColor(LIGHT_A)) {

			case BLACK:
				// 黒を検知 => 右回転 => 前進
				if (beforeA == BLACK) {
					continuityNumber++;
					rotateRight(10 + 3 * continuityNumber);
				}
				rotateRight(10);
//        forward(1);
				break;
			case WHITE:
				if (beforeA == BLACK)
					continuityNumber = 0;
				break;

//      case WHITE:
//        // 白を検知 => 左回転 => 前進
//        rotateLeft(10);
//        forward(1);
//        break;

			}

			// 右センサの色に応じて分岐
			switch (getColor(LIGHT_C)) {

			case BLACK:
				if (beforeC == BLACK) {
					continuityNumber++;
					rotateLeft(10 + 3 * continuityNumber);
				}
				rotateLeft(10);
				break;
			case WHITE:
				if (beforeC == BLACK)
					continuityNumber = 0;
				break;

			}

			beforeA = getColor(LIGHT_A);
			beforeC = getColor(LIGHT_C);
			forward(1);

			// 速度調整＆画面描画
			delay();

			// ゴールに到達すれば終了
			if (isOnGoal())
				return;

		}
	}
}
