package example.android.graphics.canvas;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

// キャンバスビュークラス
public class CanvasView extends View {
	// 描画する点格納用リスト
	private ArrayList<Point> blackPts;
	private ArrayList<Point> redPts;
	private ArrayList<Point> greenPts;
	private ArrayList<Point> bluePts;
	// Paintインスタンス
	private Paint paint;
	// タグ定数
	private static final int TAG_BLACK = 0;
	private static final int TAG_RED = 1;
	private static final int TAG_GREEN = 2;
	private static final int TAG_BLUE = 3;

	// コンストラクタ
	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);

		// 描画する点格納用リスト生成
		blackPts = new ArrayList<Point>();
		redPts = new ArrayList<Point>();
		greenPts = new ArrayList<Point>();
		bluePts = new ArrayList<Point>();

		// Paint(筆)の設定
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3);
	}

	// onMeasureメソッド(ビューのサイズ設定処理)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	// onTouchEventメソッド(タッチイベント)
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// 現在の色設定によって保持するポイントを振り分ける
			switch (paint.getColor()) {
			case Color.BLACK:
				// 画面に指が付いたまたは動いている場合はその座標を設定
				blackPts.add(new Point((int) event.getX(), (int) event.getY()));
				break;
			case Color.RED:
				redPts.add(new Point((int) event.getX(), (int) event.getY()));
				break;
			case Color.GREEN:
				greenPts.add(new Point((int) event.getX(), (int) event.getY()));
				break;
			case Color.BLUE:
				bluePts.add(new Point((int) event.getX(), (int) event.getY()));
				break;
			}
			break;

		case MotionEvent.ACTION_UP:
			switch (paint.getColor()) {
			case Color.BLACK:
				// 画面から指が離された場合はデリミタを設定
				blackPts.add(new Point(-1, -1));
				break;
			case Color.RED:
				redPts.add(new Point(-1, -1));
				break;
			case Color.GREEN:
				greenPts.add(new Point(-1, -1));
				break;
			case Color.BLUE:
				bluePts.add(new Point(-1, -1));
				break;
			}
			break;
		}

		this.invalidate();

		return true;
	}

	// onDrawメソッド(描画処理)
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// キャンバスの背景を白に設定
		canvas.drawColor(Color.WHITE);
		// 現在の色設定を保持
		int recentColor = paint.getColor();
		// 描画処理
		Point sp = new Point(-1, -1);
		// 黒の描画処理
		for (Point ep : blackPts) {
			paint.setColor(Color.BLACK);
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}
			sp = ep;
		}
		// 赤の描画処理
		sp = new Point(-1, -1);
		for (Point ep : redPts) {
			paint.setColor(Color.RED);
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}
			sp = ep;
		}
		// 緑の描画処理
		sp = new Point(-1, -1);
		for (Point ep : greenPts) {
			paint.setColor(Color.GREEN);
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}
			sp = ep;
		}
		// 青の描画処理
		sp = new Point(-1, -1);
		for (Point ep : bluePts) {
			paint.setColor(Color.BLUE);
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}
			sp = ep;
		}

		// 元の色に戻す
		paint.setColor(recentColor);
	}

	// clearDrawListメソッド(クリア処理)
	public void clearDrawList() {
		blackPts.clear();
		redPts.clear();
		greenPts.clear();
		bluePts.clear();
		this.invalidate();
	}

	public void changeColor(int colortag) {
		switch (colortag) {
		case TAG_BLACK:
			paint.setColor(Color.BLACK);
			break;
		case TAG_RED:
			paint.setColor(Color.RED);
			break;
		case TAG_GREEN:
			paint.setColor(Color.GREEN);
			break;
		case TAG_BLUE:
			paint.setColor(Color.BLUE);
			break;
		}
	}
}
