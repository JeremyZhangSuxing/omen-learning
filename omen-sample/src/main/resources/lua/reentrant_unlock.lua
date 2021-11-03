--
-- Created by IntelliJ IDEA.
-- User: suxingzhang
-- Date: 2021/10/27
-- Time: 5:05 下午
-- To change this template use File | Settings | File Templates.
--

local key = KEYS[1];
local threadId = ARGV[1];

-- lockname、threadId不存在
if (redis.call('hexists', key, threadId) == 0) then
    return nil;
end;

-- 计数器-1
local count = redis.call('hincrby', key, threadId, -1);

-- 删除lock
if (count == 0) then
    redis.call('del', key);
    return nil;
end;


