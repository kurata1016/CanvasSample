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

// �L�����o�X�r���[�N���X
public class CanvasView extends View {
	// �`�悷��_�i�[�p���X�g
	private ArrayList<Point> blackPts;
	private ArrayList<Point> redPts;
	private ArrayList<Point> greenPts;
	private ArrayList<Point> bluePts;
	// Paint�C���X�^���X
	private Paint paint;
	// �^�O�萔
	private static final int TAG_BLACK = 0;
	private static final int TAG_RED = 1;
	private static final int TAG_GREEN = 2;
	private static final int TAG_BLUE = 3;

	// �R���X�g���N�^
	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);

		// �`�悷��_�i�[�p���X�g����
		blackPts = new ArrayList<Point>();
		redPts = new ArrayList<Point>();
		greenPts = new ArrayList<Point>();
		bluePts = new ArrayList<Point>();

		// Paint(�M)�̐ݒ�
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3);
	}

	// onMeasure���\�b�h(�r���[�̃T�C�Y�ݒ菈��)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	// onTouchEvent���\�b�h(�^�b�`�C�x���g)
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// ���݂̐F�ݒ�ɂ���ĕێ�����|�C���g��U�蕪����
			switch (paint.getColor()) {
			case Color.BLACK:
				// ��ʂɎw���t�����܂��͓����Ă���ꍇ�͂��̍��W��ݒ�
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
				// ��ʂ���w�������ꂽ�ꍇ�̓f���~�^��ݒ�
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

	// onDraw���\�b�h(�`�揈��)
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// �L�����o�X�̔w�i�𔒂ɐݒ�
		canvas.drawColor(Color.WHITE);
		// ���݂̐F�ݒ��ێ�
		int recentColor = paint.getColor();
		// �`�揈��
		drawing(canvas, blackPts, Color.BLACK);
		drawing(canvas, redPts, Color.RED);
		drawing(canvas, greenPts, Color.GREEN);
		drawing(canvas, bluePts, Color.BLUE);
		// ���̐F�ɖ߂�
		paint.setColor(recentColor);
	}

	// clearDrawList���\�b�h(�N���A����)
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

	public void drawing(Canvas canvas, ArrayList<Point> points, int color) {
		Point sp = new Point(-1, -1);
		// ���̕`�揈��
		for (Point ep : points) {
			paint.setColor(color);
			if (sp.x >= 0) {
				if (ep.x >= 0) {
					canvas.drawLine(sp.x, sp.y, ep.x, ep.y, paint);
				} else {
					canvas.drawPoint(sp.x, sp.y, paint);
				}
			}
			sp = ep;
		}
	}
}
