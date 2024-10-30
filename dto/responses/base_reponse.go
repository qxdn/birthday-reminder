package responses

type BaseResponse[T BaseVO] struct {
	Data         T      `json:"data"`
	ErrorCode    int    `json:"errorCode"`
	ErrorMessage string `json:"errorMessage"`
	Success      bool   `json:"success"`
	Total        int    `json:"total"`
}
