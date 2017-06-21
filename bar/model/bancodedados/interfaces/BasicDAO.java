package bar.model.bancodedados.interfaces;

import java.util.ArrayList;

public interface BasicDAO<T> {
	boolean salvar(T dado);
	boolean atualizar(T dado);
	boolean deletar(int id);
	T buscarId(int id);
	ArrayList<T> buscarTudo();
}
