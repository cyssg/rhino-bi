package org.rhinodata.rhinobi.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.common.tool.utils.ObjectUtil;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Optional;

/**
 * 统一API响应结果封装
 *
 * @author chenye
 */
@Getter
@Setter
@ToString
@Schema(name = "返回信息", description = "返回信息")
@NoArgsConstructor
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "状态码")
	private int code;
	@Schema(description = "是否成功")
	private boolean success;
	@Schema(description = "承载数据")
	private T data;
	@Schema(description = "返回消息")
	private String msg;

	private R(IResultCode resultCode) {
		this(resultCode, null, resultCode.getMessage());
	}

	private R(IResultCode resultCode, String msg) {
		this(resultCode, null, msg);
	}

	private R(IResultCode resultCode, T data) {
		this(resultCode, data, resultCode.getMessage());
	}

	private R(IResultCode resultCode, T data, String msg) {
		this(resultCode.getCode(), data, msg);
	}

	private R(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.success = ResultCode.SUCCESS.code == code;
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result Result
	 * @return 是否成功
	 */
	public static boolean isSuccess(@Nullable R<?> result) {
		return Optional.ofNullable(result)
			.map(x -> ObjectUtil.nullSafeEquals(ResultCode.SUCCESS.code, x.code))
			.orElse(Boolean.FALSE);
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result Result
	 * @return 是否成功
	 */
	public static boolean isNotSuccess(@Nullable R<?> result) {
		return !R.isSuccess(result);
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(T data) {
		return data(data, Constant.DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(T data, String msg) {
		return data(HttpServletResponse.SC_OK, data, msg);
	}

	/**
	 * 返回R
	 *
	 * @param code 状态码
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(int code, T data, String msg) {
		return new R<>(code, data, data == null ? Constant.DEFAULT_NULL_MESSAGE : msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg 消息
	 * @param <T> T 泛型标记
	 * @return R
	 */
	public static <T> R<T> success(String msg) {
		return new R<>(ResultCode.SUCCESS, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> R<T> success(IResultCode resultCode) {
		return new R<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param msg        消息
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> R<T> success(IResultCode resultCode, String msg) {
		return new R<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg 消息
	 * @param <T> T 泛型标记
	 * @return R
	 */
	public static <T> R<T> fail(String msg) {
		return new R<>(ResultCode.FAILURE, msg);
	}


	/**
	 * 返回R
	 *
	 * @param code 状态码
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> fail(int code, String msg) {
		return new R<>(code, null, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> R<T> fail(IResultCode resultCode) {
		return new R<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param msg        消息
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> R<T> fail(IResultCode resultCode, String msg) {
		return new R<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param flag 成功状态
	 * @return R
	 */
	public static <T> R<T> status(boolean flag) {
		return flag ? success(Constant.DEFAULT_SUCCESS_MESSAGE) : fail(Constant.DEFAULT_FAILURE_MESSAGE);
	}

}
