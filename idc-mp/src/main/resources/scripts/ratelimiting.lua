-- 获取key, 限流KEY
local key = KEYS[1]
-- 获取一个参数, 限流大小, lua脚本接收到的参数会转为String, 这里转为数字类型
local limit = tonumber(ARGV[1])
-- 当前并发数,执行redis get操作
local current = tonumber(redis.call('get', key) or "0")
if current == nil then
    current = 0
end

redis.log(redis.LOG_WARNING, "key:" ..KEYS[1])
redis.log(redis.LOG_WARNING, "limit:" ..ARGV[1])
redis.log(redis.LOG_WARNING, "current:" ..current)


-- 如果超出限流大小
if current + 1 > limit then
    return 0
-- 如果没超出限流大小, 请求数+1, 并设置5秒过期
else
    redis.call("INCRBY", key,"1")
    redis.call("expire", key,"5")
    return current + 1
end