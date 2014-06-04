package example.android.graphics.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

// �L�����o�X��ʃA�N�e�B�r�e�B
public class CanvasActivity extends Activity {
	// �^�O�萔
	private static final int TAG_BLACK = 0;
	private static final int TAG_RED = 1;
	private static final int TAG_GREEN = 2;
	private static final int TAG_BLUE = 3;

	// �L�����o�X�N���A�{�^���̃N���b�N���X�i�[��`
	private OnClickListener onClickListener = new View.OnClickListener() {
		public void onClick(View arg0) {
			CanvasView view = (CanvasView) findViewById(R.id.cv_canvas);
			view.clearDrawList();
		}
	};

	// onCreate���\�b�h(��ʏ����\���C�x���g)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
		super.onCreate(savedInstanceState);
		// �`��̈���L���邽�߂Ƀ^�C�g����\���ݒ�
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ���C�A�E�g�ݒ�t�@�C����ݒ�
		setContentView(R.layout.canvassample);

		// �L�����o�X�N���A�{�^���I�u�W�F�N�g����
		Button btnClear = (Button) findViewById(R.id.btn_clear);
		btnClear.setOnClickListener(onClickListener);

		// �e��F�{�^���I�u�W�F�N�g����
		// ��
		Button btnBlack = (Button) findViewById(R.id.btn_black);
		btnBlack.setTag(TAG_BLACK);
		btnBlack.setOnClickListener(btnClickListener);

		// ��
		Button btnRed = (Button) findViewById(R.id.btn_red);
		btnRed.setTag(TAG_RED);
		btnRed.setOnClickListener(btnClickListener);

		// ��
		Button btnGreen = (Button) findViewById(R.id.btn_green);
		btnGreen.setTag(TAG_GREEN);
		btnGreen.setOnClickListener(btnClickListener);

		// ��
		Button btnBlue = (Button) findViewById(R.id.btn_blue);
		btnBlue.setTag(TAG_BLUE);
		btnBlue.setOnClickListener(btnClickListener);
	}

	private OnClickListener btnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// �^�O�擾
			int tag = (int) v.getTag();
			// �L�����o�X�I�u�W�F�N�g�擾
			CanvasView view = (CanvasView) findViewById(R.id.cv_canvas);
			// �^�O�ɂ��ϐF�U�蕪��
			switch (tag) {
			case TAG_BLACK:
				view.changeColor(TAG_BLACK);;
				Toast.makeText(CanvasActivity.this, "�F�����ɕύX���܂���", Toast.LENGTH_SHORT).show();
				break;
			case TAG_RED:
				view.changeColor(TAG_RED);
				Toast.makeText(CanvasActivity.this, "�F��ԂɕύX���܂���", Toast.LENGTH_SHORT).show();
				break;
			case TAG_GREEN:
				view.changeColor(TAG_GREEN);
				Toast.makeText(CanvasActivity.this, "�F��΂ɕύX���܂���", Toast.LENGTH_SHORT).show();
				break;
			case TAG_BLUE:
				view.changeColor(TAG_BLUE);
				Toast.makeText(CanvasActivity.this, "�F��ɕύX���܂���", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};
}
