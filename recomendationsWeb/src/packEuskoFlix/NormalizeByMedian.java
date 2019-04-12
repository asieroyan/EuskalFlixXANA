package packEuskoFlix;

import java.util.Iterator;

public class NormalizeByMedian implements NormalizeMode {
	public Matrix normalizeMatrix() {
		Matrix normalizedMatrix= new Matrix();
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector user=ratings.getAllUsers(); //lista de todos los usuarios
		Iterator<Integer> itr=user.iterator();
		while (itr.hasNext()) {
			Integer userAct=itr.next();
			this.normalizeUserRatings(userAct,normalizedMatrix);
		}
		return normalizedMatrix;
	}
	private void normalizeUserRatings(int pIdUser, Matrix pMatrix) {
		double median=this.userMedian(pIdUser);
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector films=ratings.getFilmsFromUser(pIdUser);
		Iterator<Integer> itr=films.iterator();
		while (itr.hasNext()) {
			Integer filmAct=itr.next();
			double valorationAct=ratings.getValoration(pIdUser, filmAct);
			valorationAct=valorationAct-median;
			pMatrix.addData(pIdUser, filmAct, valorationAct);
		}
	}
	private double userMedian(int pIdUser) {
		double median=0.0;
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector films=ratings.getFilmsFromUser(pIdUser);
		int cant=films.size();
		Iterator<Integer> itr=films.iterator();
		while (itr.hasNext()) {
			Integer filmAct=itr.next();
			double valorationAct=ratings.getValoration(pIdUser, filmAct);
			median+=valorationAct;
		}
		return median/cant;
	}
	public Matrix unNormalizeMatrix(int pIdUser,Matrix pMatrix) {
		double median=this.userMedian(pIdUser);
		Vector filmList=pMatrix.getSecondKeyList(pIdUser);
		Iterator<Integer> itr=filmList.iterator();
		while (itr.hasNext()) {
			Integer act= itr.next();
			double valorationAct=pMatrix.getValue(pIdUser,act);
			valorationAct=valorationAct+median;
			pMatrix.changeValue(pIdUser, act, valorationAct);
		}
		return pMatrix;
	}
}
