/**
 * @Package_name   : model
 * @Class_name     : LottoController
 * <p>
 * Create Date : 2023-11-07
 * Create User : 정은채
 */
package controller;

import java.util.ArrayList;

import java.util.Map;
import model.Lotto;
import model.LottoNumberPicker;
import model.LottoNumberComparater;
import model.LottoNumberSetHolder;
import model.LottoResultJudger;
import model.LottoTicketSeller;
import model.LottoWinResultsHolder;
import view.InputView;
import view.OutputView;

public class LottoController {
    /**
     * Description : 로또 로직 컨트롤러
     *
     * @Method : controlLotto()
     */
    public void controlLotto(){
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        outputView.printLottoBuy();
        int money = inputView.inputLottoBuy();

        LottoTicketSeller lottoTicketSeller = new LottoTicketSeller(money);
        int lottoTicketNum = lottoTicketSeller.receiveLottotickets();

        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        LottoNumberSetHolder lottoNumberSetHolder = new LottoNumberSetHolder();
        while(lottoTicketNum-- > 0){
            Lotto lotto = new Lotto(lottoNumberPicker.lottoNumberPick());
            lottoNumberSetHolder.lottoNumberSetAdd(lotto);
        }

        outputView.printLottoNumRequest();
        ArrayList<Integer> resultLottoNum = inputView.inputLottoNum();
        outputView.printBonusNumRequest();
        int resultBonusNum = inputView.inputBonusNum();
        LottoNumberComparater lottoNumberComparater = new LottoNumberComparater(resultLottoNum, resultBonusNum);

        // lottoNumberSetHolder에 담긴 lotto들을 LottoNumberComparater에 넣어서 값을 받아 LottoWinResultsHolder에 넣기
        ArrayList<Lotto> LottoNumberSet = lottoNumberSetHolder.getLottoNumberSet();
        LottoWinResultsHolder lottoWinResultsHolder = new LottoWinResultsHolder();
        for (Lotto lotto : LottoNumberSet){
            String LottoWinResult = lottoNumberComparater.comparaterLottoResult(lotto.getLottoNumbers());
            lottoWinResultsHolder.lottoWinResultsAdd(LottoWinResult);
        }

        Map<String, Integer> winResults = lottoWinResultsHolder.getWinResults();
        //outputView.

        // lottoWinResultsHolder에서 전체 결과 맵 가져와서 LottoResultJudger 이용해 톡계출력

    }
}
