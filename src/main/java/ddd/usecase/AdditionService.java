package ddd.usecase;

import ddd.domains.AdditionElement;

public class AdditionService {
    public AdditionService(){}

    public int execute(int int1, int int2) throws AdditionServiceException {
        try {
            // ドメインオブジェクトを生成
            final AdditionElement e1 = new AdditionElement(int1); // 値オブジェクト
            final AdditionElement e2 = new AdditionElement(int2); // 値オブジェクト

            // ドメインオブジェクトから結果を取得
            final AdditionElement result = e1.plus(e2); // 値オブジェクトのメソッド戻り値も値オブジェクト

            return result.getValue();
        } catch (IllegalArgumentException e) {
            throw new AdditionServiceException(e);
        }
    }
}
