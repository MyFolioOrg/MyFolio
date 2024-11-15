package me.may.myfolio.portfolio.mapper;

import me.may.myfolio.common.mapper.Mapper;
import me.may.myfolio.portfolio.domain.dto.PortfolioDTO;
import me.may.myfolio.portfolio.domain.entity.Portfolio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper implements Mapper<Portfolio, PortfolioDTO> {
    private final ModelMapper modelMapper;
    public PortfolioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public PortfolioDTO mapTo(Portfolio portfolio) {
        return modelMapper.map(portfolio, PortfolioDTO.class);
    }

    @Override
    public Portfolio mapFrom(PortfolioDTO portfolioDTO) {
        return modelMapper.map(portfolioDTO, Portfolio.class);
    }
}
