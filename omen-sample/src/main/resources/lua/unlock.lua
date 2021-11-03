--
-- Created by IntelliJ IDEA.
-- User: suxingzhang
-- Date: 2021/10/27
-- Time: 4:49 下午
-- To change this template use File | Settings | File Templates.
--
if 1 == redis.call('setnx', KEYS[1], ARGV[1]) then
    redis.call('pexpire', KEYS[1], ARGV[2])
    return 'true'
else
    return 'false'
end


