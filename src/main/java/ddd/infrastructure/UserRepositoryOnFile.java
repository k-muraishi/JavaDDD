package ddd.infrastructure;

import ddd.domains.AdditionElement;
import ddd.domains.AdditionFormula;
import ddd.domains.User;
import ddd.repositories.UserRepository;
import ddd.repositories.UserRepositoryException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserRepositoryOnFile  implements UserRepository {
    public void save(User user) throws UserRepositoryException {
        String filename = user.getName() + ".csv";
        try(FileWriter fw = new FileWriter(filename)) {
            for (AdditionFormula formula : user.getHistory()) {
                String[] arr = {
                        String.valueOf(formula.getElement1Value()),
                        String.valueOf(formula.getElement2Value()),
                        String.valueOf(formula.getResultValue())
                };
                String csv = String.join(",", arr);
                fw.write(csv + "\n");
            }
        } catch (IOException e) {
            throw new UserRepositoryException(e);
        }
    }

    public User find(String name) throws UserRepositoryException {
        User user = new User(name);

        File file = new File("./" + name + ".csv");
        if (file.exists()) {
            try {
                List<List<String>> records = readCsvFile(file);
                addHistoryTo(user, records);
            } catch (IOException e) {
                throw new UserRepositoryException(e);
            }
        }

        return user;
    }

    private List<List<String>> readCsvFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<List<String>> records = new ArrayList<>();

        String line;
        StringTokenizer token;
        while ((line = br.readLine()) != null) {
            List<String> record = new ArrayList<>();
            token = new StringTokenizer(line, ",");
            while (token.hasMoreTokens()) {
                record.add(token.nextToken());
            }
            records.add(record);
        }

        br.close();
        return records;
    }

    private void addHistoryTo(User user, List<List<String>> records) {
        records.forEach(record -> {
            user.addHistory(
                    new AdditionElement(Integer.parseInt(record.get(0))),
                    new AdditionElement(Integer.parseInt(record.get(1))),
                    new AdditionElement(Integer.parseInt(record.get(2)))
            );
        });
    }
}
