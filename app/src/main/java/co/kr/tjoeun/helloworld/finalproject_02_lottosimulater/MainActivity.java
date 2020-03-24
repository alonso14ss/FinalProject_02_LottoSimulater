package co.kr.tjoeun.helloworld.finalproject_02_lottosimulater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.kr.tjoeun.helloworld.finalproject_02_lottosimulater.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    List<TextView> winNumTxtList = new ArrayList<>();

    int[] winLottoNumArr = new int[6];
    int bonusNum = 0;

    int[] myLottoNumArr = {4, 14, 20, 26, 33, 45};

    long userMoneyAmount = 0;

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        binding.buyOneLottoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeWinLottoNum();

//                당첨 번호를 생성 => 텍스트 뷰에 반영

//                몇등인지 판단
                checkLottoRank();

            }
        });

    }

    @Override
    public void setValues() {

        winNumTxtList.add(binding.winLottonNumTxt01);
        winNumTxtList.add(binding.winLottonNumTxt02);
        winNumTxtList.add(binding.winLottonNumTxt03);
        winNumTxtList.add(binding.winLottonNumTxt04);
        winNumTxtList.add(binding.winLottonNumTxt05);
        winNumTxtList.add(binding.winLottonNumTxt06);

    }

    void makeWinLottoNum() {

//        6개의 숫자(배열) + 보너스 번호 1개 int변수
//        => 이 함수에서만이 아니라 다른곳에서도 쓸 예정
//        => 당첨 등수 확인때도 사용 => 멤버변수로 배열/변수 생성

//        당첨번호 + 보너스 번호를 모두 0 으로 초기화
//        이미 뽑은 번호가 있다면 모두 날리자

        for (int i = 0; i < winLottoNumArr.length; i++) {
            winLottoNumArr[i] = 0;
        }
        bonusNum = 0;

//        로또번호 6개 생성

        for (int i = 0; i < winLottoNumArr.length; i++) {
//            1 ~ 45 사이의 숫자를 랜덤으로 뽑고
//            중복이 아니라면 => 당첨번호로 선정
//            중복 ? => 다시뽑아 => 중복이 아닐때까지 계속뽑아라

            while (true) {

//             1~45의 정수를 랜덤으로 뽑아서 임시저장
//             이 숫자가 중복검사를 통과하면 사용 아니면 다시
                int randomNum = (int) (Math.random() * 45 - 1);

//             중복검사? 당첨번호 전부와 randomNum 비교
//             하나라도 같으면 탈락
                Boolean isDuplOk = true;
                for (int winNum : winLottoNumArr) {
                    if (winNum == randomNum) {
                        isDuplOk = false;
                        break;
                    }
                }

                if (isDuplOk) {
                    winLottoNumArr[i] = randomNum;
                    Log.i("당첨번호", randomNum + "");
                    break;
                }

            }

        }

//        6개의 당첨번호를 작은 숫자부터 정렬
        Arrays.sort(winLottoNumArr);

        for (int i = 0; i < winLottoNumArr.length; i++) {

            winNumTxtList.get(i).setText(winLottoNumArr[i] + "");

        }

//        1~45 보너스번호, 당첨번호 중복 x
        while (true) {
            int randomNum = (int) (Math.random() * 45 + 1);

            boolean isDuplOk = true;
            for (int winNum : winLottoNumArr) {
                if (winNum == randomNum) {
                    isDuplOk = false;
                    break;
                }
            }
            if (isDuplOk) {
                bonusNum = randomNum;
                break;
            }

        }

        binding.bonusNumTxt.setText(bonusNum + "");

    }

    void checkLottoRank() {
//        등수 확인 + 돈을 천원 지불
        userMoneyAmount += 1000;

        binding.useMoneyTxt.setText(String.format("사용금액 : %d원", userMoneyAmount));

//        몇등인지?
//        내 번호를 하나 들고 => 당첨번호 6개를 돌아봄
//        몇개의 숫자를 맞췄는지

        int correctCount = 0;
        for (int myNum : myLottoNumArr) {
            for (int winNum : winLottoNumArr) {

                if (myNum == winNum) {
                    correctCount++;
                }
            }
        }

//        correctCount 의 값에따라 등수를 판정
        if (correctCount == 6) {

        } else if (correctCount == 5) {
//            2등인지 3등인지 재검사 필요= > 보너스 번호를 맞췄는가
        } else if (correctCount == 4) {

        } else if (correctCount ==3) {

        }else {

        }
    }
}
