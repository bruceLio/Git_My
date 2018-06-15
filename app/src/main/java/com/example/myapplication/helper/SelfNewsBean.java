package com.example.myapplication.helper;

import java.util.List;

public class SelfNewsBean {

	/**
	 * topic : 乐迷站稳了！2018天漠音乐节全阵容大曝光
	 * id : 44
	 * source : 搜狐娱乐自媒体
	 * miniimg : [{"imgwidth":150,"imgheight":100,"src":"http://image.huadongmedia.com/plat/cover/image/20180426/cf44e871ca944c0094449d09bca96db7.jpg"}]
	 * type : 娱乐
	 * date : 1524737807000
	 * coverType : 2
	 * url : http://211.95.56.10:9996/admin/headLine/h5Api/44
	 *  total_comment : 14780.0
	 */

	private String topic;
	private int id;
	private String source;
	private String type;
	private long date;
	private String coverType;
	private String url;
	private List<MiniimgBean> miniimg;
	private double total_comment;
	private long articlePublishTime;

	public long getArticlePublishTime() {
		return articlePublishTime;
	}

	public void setArticlePublishTime(long articlePublishTime) {
		this.articlePublishTime = articlePublishTime;
	}

	public double getTotal_comment() {
		return total_comment;
	}

	public void setTotal_comment(double total_comment) {
		this.total_comment = total_comment;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MiniimgBean> getMiniimg() {
		return miniimg;
	}

	public void setMiniimg(List<MiniimgBean> miniimg) {
		this.miniimg = miniimg;
	}

	public static class MiniimgBean {
		/**
		 * imgwidth : 150
		 * imgheight : 100
		 * src : http://image.huadongmedia.com/plat/cover/image/20180426/cf44e871ca944c0094449d09bca96db7.jpg
		 */

		private int imgwidth;
		private int imgheight;
		private String src;

		public int getImgwidth() {
			return imgwidth;
		}

		public void setImgwidth(int imgwidth) {
			this.imgwidth = imgwidth;
		}

		public int getImgheight() {
			return imgheight;
		}

		public void setImgheight(int imgheight) {
			this.imgheight = imgheight;
		}

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}
	}
}
