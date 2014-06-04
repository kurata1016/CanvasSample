package example.android.graphics.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

// キャンバス画面アクティビティ
public class CanvasActivity extends Activity {
	// タグ定数
	private static final int TAG_BLACK = 0;
	private static final int TAG_RED = 1;
	private static final int TAG_GREEN = 2;
	private static final int TAG_BLUE = 3;

	// キャンバスクリアボタンのクリックリスナー定義
	private OnClickListener onClickListener = new View.OnClickListener() {
		public void onClick(View arg0) {
			CanvasView view = (CanvasView) findViewById(R.id.cv_canvas);
			view.clearDrawList();
		}
	};

	// onCreateメソッド(画面初期表示イベント)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// スーパークラスのonCreateメソッド呼び出し
		super.onCreate(savedInstanceState);
		// 描画領域を広げるためにタイトル非表示設定
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// レイアウト設定ファイルを設定
		setContentView(R.layout.canvassample);

		// キャンバスクリアボタンオブジェクト生成
		Button btnClear = (Button) findViewById(R.id.btn_clear);
		btnClear.setOnClickListener(onClickListener);

		// 各種色ボタンオブジェクト生成
		// 黒
		Button btnBlack = (Button) findViewById(R.id.btn_black);
		btnBlack.setTag(TAG_BLACK);
		btnBlack.setOnClickListener(btnClickListener);

		// 赤
		Button btnRed = (Button) findViewById(R.id.btn_red);
		btnRed.setTag(TAG_RED);
		btnRed.setOnClickListener(btnClickListener);

		// 緑
		Button btnGreen = (Button) findViewById(R.id.btn_green);
		btnGreen.setTag(TAG_GREEN);
		btnGreen.setOnClickListener(btnClickListener);

		// 青
		Button btnBlue = (Button) findViewById(R.id.btn_blue);
		btnBlue.setTag(TAG_BLUE);
		btnBlue.setOnClickListener(btnClickListener);
	}

	private OnClickListener btnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// タグ取得
			int tag = (int) v.getTag();
			// キャンバスオブジェクト取得
			CanvasView view = (CanvasView) findViewById(R.id.cv_canvas);
			// タグにより変色振り分け
			switch (tag) {
			case TAG_BLACK:
				view.changeColor(TAG_BLACK);;
				Toast.makeText(CanvasActivity.this, "色を黒に変更しました", Toast.LENGTH_SHORT).show();
				break;
			case TAG_RED:
				view.changeColor(TAG_RED);
				Toast.makeText(CanvasActivity.this, "色を赤に変更しました", Toast.LENGTH_SHORT).show();
				break;
			case TAG_GREEN:
				view.changeColor(TAG_GREEN);
				Toast.makeText(CanvasActivity.this, "色を緑に変更しました", Toast.LENGTH_SHORT).show();
				break;
			case TAG_BLUE:
				view.changeColor(TAG_BLUE);
				Toast.makeText(CanvasActivity.this, "色を青に変更しました", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};
}
