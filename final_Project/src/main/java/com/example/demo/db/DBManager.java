package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.example.demo.vo.CartProductVO;
import com.example.demo.vo.CartVO;
import com.example.demo.vo.CategorySaleVO;
import com.example.demo.vo.ContentReviewVO;
import com.example.demo.vo.CustomerOrder_detailVO;
import com.example.demo.vo.CustomerOrder_refundVO;
import com.example.demo.vo.CustomerVO;
import com.example.demo.vo.Customer_orderVO;
import com.example.demo.vo.ListDetailVO;
import com.example.demo.vo.ListOrderVO;
import com.example.demo.vo.ListQnaVO;
import com.example.demo.vo.ListReviewVO;
import com.example.demo.vo.MarginProductVO;
import com.example.demo.vo.MonthTotalVO;
import com.example.demo.vo.OrderCancelVO;
import com.example.demo.vo.ProductVO;
import com.example.demo.vo.QnaVO;
import com.example.demo.vo.ReviewVO;


public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	//=========================================
	//product
	
	public static List<ProductVO> listProduct(HashMap map){
		SqlSession session = factory.openSession();
		List<ProductVO> list = session.selectList("product.listProduct",map);
		System.out.println(list);
		System.out.println("데이터수:"+list.size());
		session.close();
		return list;
	}
	
	public static int getTotalRecord(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("product.getTotalRecord", map);
		System.out.println("totalRecord:"+no);
		session.close();
		return no;
	}
	
	public static List<ProductVO> recentProduct(String orderType){
		SqlSession session = factory.openSession();
		List<ProductVO> list = session.selectList("product.recentProduct",orderType);
		session.close();
		return list;
	}
	
	public static ProductVO detailProduct(int product_no) {
		SqlSession session = factory.openSession();
		ProductVO p = session.selectOne("product.detailProduct", product_no);
		session.close();
		return p;
	}
	
	public static List<ProductVO> bestProduct(){
		SqlSession session = factory.openSession();
		List<ProductVO> list = session.selectList("product.bestProduct");
		session.close();
		return list;
	}
	
	public static List<MarginProductVO> marginProduct(){
		SqlSession session = factory.openSession();
		List<MarginProductVO> list = session.selectList("product.marginProduct");
		session.close();
		return list;
	}
	
	public static List<ProductVO> searchProduct(String keyword){
		SqlSession session = factory.openSession();
		List<ProductVO> list = session.selectList("product.searchProduct", keyword);
		session.close();
		return list;
	}
	
	public static List<ProductVO> mgr_listProduct(HashMap map){
		SqlSession session = factory.openSession();
		List<ProductVO> list = session.selectList("product.mgr_listProduct",map);
		session.close();
		return list;
	}
	
	public static int mgr_getTotalRecord() {
		SqlSession session = factory.openSession();
		int no = session.selectOne("product.mgr_getTotalRecord");
		System.out.println("mgr_totalRecord:"+no);
		session.close();
		return no;
	}
	
	public static int mgr_insertProduct(ProductVO p) {
		p.setProduct_no(product_getNextNo());
		SqlSession session = factory.openSession();
		int re = session.insert("product.mgr_insertProduct",p);
		session.commit();
		session.close();
		return re;
	}
	
	public static ProductVO mgr_detailProduct(int product_no){
		SqlSession session = factory.openSession();
		ProductVO p = session.selectOne("product.mgr_detailProduct",product_no);
		session.close();
		return p;
	}
	
	public static int mgr_updateProduct(ProductVO p) {
		SqlSession session = factory.openSession();
		int re = session.update("product.mgr_updateProduct",p);
		session.commit();
		session.close();
		return re;
	}

	public static int mgr_deleteProduct(int product_no) {
		SqlSession session = factory.openSession();
		int re = session.delete("product.mgr_deleteProduct",product_no);
		session.commit();
		session.close();
		return re;
	}
	
	public static int product_getNextNo() {
		SqlSession session = factory.openSession();
		int product_no = session.selectOne("product.getNextNo");
		session.close();
		return product_no;
	}
	
	public static List<CategorySaleVO> category_sale(String category_code){
		SqlSession session = factory.openSession();
		List<CategorySaleVO> list = session.selectList("product.category_sale",category_code);
		session.close();
		return list;
	}
	
	//=========================================
	//review
	
	public static List<ListReviewVO> listReviewWrite(HashMap map){
		SqlSession session = factory.openSession();
		List<ListReviewVO> list = session.selectList("review.listReviewWrite",map);
		session.close();
		return list;
	}
	
	public static int review_getTotalRecord(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("review.review_getTotalRecord", map);
		session.close();
		return re;
	}
	
	public static List<ContentReviewVO> listReviewComplete(String cust_id){
		SqlSession session = factory.openSession();
		List<ContentReviewVO> list = session.selectList("review.listReviewComplete", cust_id);
		session.close();
		return list;
	}
	
	public static int deleteReview(int review_no) {
		SqlSession session = factory.openSession();
		int re = session.delete("review.deleteReview", review_no);
		session.commit();
		session.close();
		return re;
	}
	
	public static ContentReviewVO contentReview(int review_no) {
		SqlSession session = factory.openSession();
		ContentReviewVO c = session.selectOne("review.contentReview", review_no);
		session.close();
		return c;
	}
	
	public static int updateReview(ReviewVO r) {
		SqlSession session = factory.openSession();
		int re = session.update("review.updateReview", r);
		session.commit();
		session.close();
		return re;
	}
	
	public static ReviewVO findByNo(int review_no) {
		SqlSession session = factory.openSession();
		ReviewVO r = session.selectOne("review.findByNo", review_no);
		session.close();
		return r;
	}
	
	public static List<ContentReviewVO> findAllReview(int product_no){
		SqlSession session = factory.openSession();
		List<ContentReviewVO> list = session.selectList("review.findAllReview", product_no);
		session.close();
		return list;
	}
	
	public static int review_getNextNo() {
		SqlSession session = factory.openSession();
		int review_no = session.selectOne("review.getNextNo");
		session.close();
		return review_no;
	}
	
	public static int defaultReview(ReviewVO r) {
		SqlSession session = factory.openSession();
		int re= session.insert("review.defaultReview", r);
		session.commit();
		session.close();
		return re;
	}
	
	

	
	//==============================================
	//customer
	
	public static int insertCustomer(CustomerVO c) {
		SqlSession session = factory.openSession();
		int re = session.insert("customer.insertCustomer",c);
		session.commit();
		session.close();
		return re;
	}
	
	public static int doubleCheck_id(String cust_id) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("customer.doubleCheck_id",cust_id);
		session.close();
		return re;
	}
	
	public static int doubleCheck_email(String cust_email) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("customer.doubleCheck_email",cust_email);
		session.close();
		return re;
	}
	
	public static int login(String cust_id, String cust_pwd) {
		int re = -1;
		SqlSession session = factory.openSession();
		String dbPwd = session.selectOne("customer.login",cust_id);
		session.close();
		if(dbPwd != null) { //dbPwd != null이면 re는 -1이 반환
			if(dbPwd.equals(cust_pwd)) {
				re = 1;
			}else {
				re = 0;
			}
		}
		return re;
	}
	
	public static String findId(HashMap map) {
		SqlSession session = factory.openSession();
		String cust_id = session.selectOne("customer.findId",map);
		session.close();
		return cust_id;
	}
	
	public static String findPwd(HashMap map) {
		SqlSession session = factory.openSession();
		String cust_pwd = session.selectOne("customer.findPwd",map);
		session.close();
		return cust_pwd;
	}
	
	public static CustomerVO detailCustomer(String cust_id) {
		SqlSession session = factory.openSession();
		CustomerVO c = session.selectOne("customer.detailCustomer",cust_id);
		session.close();
		return c;
	}
	
	public static int updateCustomer(CustomerVO c) {
		SqlSession session = factory.openSession();
		int re = session.update("customer.updateCustomer",c);
		session.commit();
		session.close();
		return re;
	}
	
	public static CustomerVO showCustomer(String cust_id) {
		SqlSession session = factory.openSession();
		CustomerVO c = session.selectOne("customer.showCustomer",cust_id);
		session.close();
		return c;
	}
	

	public static String getRole(String cust_id) {
		SqlSession session = factory.openSession();
		String role = session.selectOne("customer.getRole",cust_id);
		session.close();
		return role;
	}
	public static int mypageMain(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("customer.mypage_login",map);
		session.close();
		return re;

	}
	
	public static String getEmail(String cust_id) {
		SqlSession session = factory.openSession();
		String email = session.selectOne("customer.getEmail",cust_id);
		session.close();
		return email;
	}
	
	//===================================================
	//qna
	
	public static QnaVO detailQna(int qna_no) {
		SqlSession session = factory.openSession();
		QnaVO q = session.selectOne("qna.detailQna",qna_no);
		session.close();
		return q;
	}
	
	public static int updateQna(QnaVO q) {
		SqlSession session = factory.openSession();
		int re = session.update("qna.updateQna",q);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteQna(int qna_no) {
		SqlSession session = factory.openSession();
		int re = session.delete("qna.deleteQna",qna_no);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextNo() {
		SqlSession session = factory.openSession();
		int no = session.selectOne("qna.getNextNo");
		session.close();
		return no;
	}
	
//	public static List<ProductVO> listProduct(HashMap map){
//		SqlSession session = factory.openSession();
//		List<ProductVO> list = session.selectList("product.listProduct",map);
//		System.out.println(list);
//		System.out.println("데이터수:"+list.size());
//		session.close();
//		return list;
//	}
	
	public static List<QnaVO> listQna(HashMap map) {
		SqlSession session = factory.openSession();
		List<QnaVO> list = session.selectList("qna.listQna",map);
		session.close();
		return list;
	}
	
	public static int QnaGetTotalRecord(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("qna.QnaGetTotalRecord", map);
		System.out.println("totalRecord:"+no);
		session.close();
		return no;
	}
	
	public static int QnaGetTotalRecord2(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("qna.QnaGetTotalRecord2", map);
		System.out.println("totalRecord:"+no);
		session.close();
		return no;
	}
	
//	public static List<QnaVO> listQna(String cust_id) {
//		SqlSession session = factory.openSession();
//		List<QnaVO> list = session.selectList("qna.listQna",cust_id);
//		session.close();
//		return list;
//	}
	
	public static int insertQna(QnaVO q) {
		SqlSession session = factory.openSession();
		q.setQna_no(getNextNo());
		int re = session.insert("qna.insertQna",q);
		session.commit();
		session.close();
		return re;
	}
	

	public static List<ListQnaVO> findAllQna(int product_no){
		SqlSession session = factory.openSession();
		List<ListQnaVO> list = session.selectList("qna.findAllQna", product_no);
		session.close();
		return list;
	}
	
	public static int updateQna_answer(QnaVO q) {
		SqlSession session = factory.openSession();
		int re=session.update("qna.updateQna_answer",q);
		session.commit();
		session.close();
		return re;
	}
	
	public static List<QnaVO> mgr_listQna(HashMap map){
		SqlSession session = factory.openSession();
		List<QnaVO> list = session.selectList("qna.mgr_listQna",map);
		session.close();
		return list;
	}
	
	public static QnaVO mgr_detailQna(int qna_no) {
		SqlSession session = factory.openSession();
		QnaVO q = session.selectOne("qna.mgr_detailQna",qna_no);
		session.close();
		return q;
	}
	
	//===========================================
	//customer_order
	
	public static List<ListOrderVO> listOrder(String cust_id) {
		SqlSession session = factory.openSession();
		List<ListOrderVO> list = session.selectList("customer_order.listOrder",cust_id);
		session.close();
		return list;
	}
	
	public static List<OrderCancelVO> orderCancelPage(int order_no) {
		SqlSession session = factory.openSession();
		List<OrderCancelVO> list = session.selectList("customer_order.orderCancelPage", order_no);
		session.close();
		return list;
	}
	
	public static int orderCancelCheck(int order_no) {
		SqlSession session = factory.openSession();
		int re = session.update("customer_order.orderCancelCheck",order_no);
		session.commit();
		session.close();
		return re;
	}
	
	public static List<MonthTotalVO> monthTotal() {
		SqlSession session = factory.openSession();
		List<MonthTotalVO> list = session.selectList("customer_order.monthTotal");
		session.close();
		return list;
	}
	
	
	
	public static int insertCustomer_order(Customer_orderVO co) {
		SqlSession session = factory.openSession();
		int re = session.insert("customer_order.insertCustomer_order", co);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getOrder_count() {
		SqlSession session = factory.openSession();
		int re= session.selectOne("customer_order.getOrder_count");
		session.close();
		return re;
	}
	
	public static int order_getNextNo() {
		SqlSession session = factory.openSession();
		int order_no = session.selectOne("customer_order.getNextNo");
		session.close();
		return order_no;
	}
	


	
	//=========================================
	//customerOrder_detail
	
	public static List<ListDetailVO> listDetail(int order_no) {
		SqlSession session = factory.openSession();
		List<ListDetailVO> list = session.selectList("customerOrder_detail.listDetail", order_no);
		session.close();
		return list;
	}
	
	public static int totalDetail(int order_no) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("customerOrder_detail.totalDetail", order_no);
		session.close();
		return re;
	}
	
	public static int insertCustomerOrder_detail(CustomerOrder_detailVO cd) {
		SqlSession session = factory.openSession();
		int re=session.insert("customerOrder_detail.insertCustomerOrder_detail", cd);
		session.commit();
		session.close();
		return re;
	}
	
	public static int orderdetail_getNextNo() {
		SqlSession session = factory.openSession();
		int detail_no = session.selectOne("customerOrder_detail.getNextNo");
		session.close();
		return detail_no;
	}
	
	//===========================================
	//customerOrder_refund
	
	public static int insertRefund(CustomerOrder_refundVO cr) {
	//	cr.setRefund_no(getNextNo());
		SqlSession session = factory.openSession();
		int re = session.insert("customerOrder_refund.insertRefund", cr);
		session.commit();
		session.close();
		return re;
	}
	
	public static int refund_getNextNo() {
		SqlSession session = factory.openSession();
		int refund_no = session.selectOne("customerOrder_refund.getNextNo");
		session.close();
		return refund_no;
	}
	
	//===========================================
	//cart
	
	public static int insertCart(CartVO c) {
		SqlSession session = factory.openSession();
		int re = session.insert("cart.insertCart", c);
		session.commit();
		session.close();
		return re;
	}
	
	public static List<CartProductVO> cartProduct(String cust_id){
		SqlSession session = factory.openSession();
		List<CartProductVO> list= session.selectList("cart.cartProduct",cust_id);
		session.close();
		return list;
	}
	
	public static int updateCart(CartVO c) {
		SqlSession session = factory.openSession();
		int re=session.update("cart.updateCart", c);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteCart(int cart_no) {
		SqlSession session = factory.openSession();
		int re=session.delete("cart.deleteCart",cart_no);
		session.commit();
		session.close();
		return re;
		}
	
	public static CartProductVO cartOrder(CartVO c) {
		SqlSession session = factory.openSession();
		CartProductVO cp= session.selectOne("cart.cartOrder", c);
		session.commit();
		session.close();
		return cp;
	}
	
	public static int cartGetNextNo() {
		SqlSession session = factory.openSession();
		int re = session.selectOne("cart.cartGetNextNo");
		session.close();
		return re;
	}
	
	public static int findByProduct(String cust_id,int product_no) {
		SqlSession session = factory.openSession();
		HashMap map = new HashMap();
		map.put("cust_id",cust_id);
		map.put("product_no",product_no);
		int re = session.selectOne("cart.findByProduct",map );
		session.close();
		return re;
	}
	

	
	
}
