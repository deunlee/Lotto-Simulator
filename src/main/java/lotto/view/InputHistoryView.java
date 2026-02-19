package lotto.view;

import lotto.model.LottoNumber;
import lotto.model.LottoNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHistoryView {
    private final Scanner scanner;

    public InputHistoryView() {
        this.scanner = new Scanner(System.in);
    }

    public List<Integer> inputWinningNumbers() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String[] winningNumbers = scanner.nextLine().split(",");
            List<Integer> parsedWinningNumbers = parseWinningNumbers(winningNumbers);
            validateWinningNumbersDistinct(parsedWinningNumbers);
            return parsedWinningNumbers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    public int inputBonusNumber(List<Integer> winningNumbers) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            int bonusNumber = parseBonusNumber(scanner.nextLine());
            validateBonusNumberDistinct(winningNumbers, bonusNumber);
            return bonusNumber;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputBonusNumber(winningNumbers);
        }
    }

    private List<Integer> parseWinningNumbers(String[] winningNumbers) {
        if (winningNumbers.length != LottoNumbers.LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 숫자 " + LottoNumbers.LOTTO_SIZE + "개를 입력해야 합니다.");
        }
        List<Integer> parsedWinningNumbers = new ArrayList<>();
        for (final String number : winningNumbers) {
            parsedWinningNumbers.add(parseWinningNumber(number));
        }
        return parsedWinningNumbers;
    }

    private int parseWinningNumber(String winningNumber) {
        try {
            int value = Integer.parseInt(winningNumber.trim());
            return new LottoNumber(value).value();
        } catch (Exception e) {
            throw new IllegalArgumentException("당첨 번호는 쉼표로 구분된 숫자만 입력해야 합니다.");
        }
    }

    private int parseBonusNumber(String bonusInput) {
        try {
            int value = Integer.parseInt(bonusInput.trim());
            return new LottoNumber(value).value();
        } catch (Exception e) {
            throw new IllegalArgumentException("보너스 볼은 공백 없이 숫자만 입력해야 합니다.");
        }
    }

    private void validateWinningNumbersDistinct(List<Integer> winningNumbers) {
        long distinctCount = winningNumbers.stream().distinct().count();
        if (distinctCount != winningNumbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateBonusNumberDistinct(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
